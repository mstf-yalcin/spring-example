package com.spring.jwt.demo.service;


import com.spring.jwt.demo.dto.CustomResponseDto;
import com.spring.jwt.demo.dto.dtoConverter.RatingDtoConverter;
import com.spring.jwt.demo.dto.request.CreateRatingRequestDto;
import com.spring.jwt.demo.dto.request.UpdateRatingRequestDto;
import com.spring.jwt.demo.dto.response.BestRatingResponseDto;
import com.spring.jwt.demo.dto.response.RatingResponseDto;
import com.spring.jwt.demo.entity.FoodRestaurant;
import com.spring.jwt.demo.entity.Rating;
import com.spring.jwt.demo.entity.Restaurant;
import com.spring.jwt.demo.entity.User;
import com.spring.jwt.demo.exception.NotFoundException;
import com.spring.jwt.demo.jwt.JwtService;
import com.spring.jwt.demo.repository.RatingRepostiory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private RatingRepostiory ratingRepostiory;
    private RatingDtoConverter ratingDtoConverter;
    private UserService userService;
    private FoodRestaurantService foodRestaurantService;
    private RestaurantService restaurantService;
    private FoodService foodService;
    private JwtService jwtService;

    public RatingService(RatingRepostiory ratingRepostiory, RatingDtoConverter ratingDtoConverter, UserService userService, FoodRestaurantService foodRestaurantService, RestaurantService restaurantService, FoodService foodService, JwtService jwtService) {
        this.ratingRepostiory = ratingRepostiory;
        this.ratingDtoConverter = ratingDtoConverter;
        this.userService = userService;
        this.foodRestaurantService = foodRestaurantService;
        this.restaurantService = restaurantService;
        this.foodService = foodService;
        this.jwtService = jwtService;
    }


    public CustomResponseDto<RatingResponseDto> createRating(CreateRatingRequestDto createRatingRequestDto) {

        Rating rating;
        Optional<Rating> exists = existsRating(createRatingRequestDto.userId(), createRatingRequestDto.foodRestaurantId());
        if (exists.isEmpty()) {
            {
                User user = userService.getById(createRatingRequestDto.userId());
                FoodRestaurant foodRestaurant = foodRestaurantService.getById(createRatingRequestDto.foodRestaurantId());
                rating = ratingRepostiory.save(Rating.builder()
                        .user(user).foodRestaurant(foodRestaurant).rating(createRatingRequestDto.rating()).build());
            }
        } else {
            rating = exists.get();
            rating.setRating(createRatingRequestDto.rating());
            ratingRepostiory.save(rating);
        }

        return CustomResponseDto.Success(201, ratingDtoConverter.convertToRatingDto(rating));
    }

    public CustomResponseDto<List<RatingResponseDto>> getAllRatings() {
        List<Rating> ratings = ratingRepostiory.findAll();
        return CustomResponseDto.Success(200, ratingDtoConverter.convertToRatingDto(ratings));
    }


    public CustomResponseDto<List<RatingResponseDto>> getAllRatingsByFoodId(long foodId, long restaurantId) {

        var foodRestaurant = foodRestaurantService.getByFoodIdAndRestaurantId(foodId, restaurantId);
        List<Rating> ratings = ratingRepostiory.findByFoodRestaurantId(foodRestaurant.getId()).stream().toList();
        return CustomResponseDto.Success(200, ratingDtoConverter.convertToRatingDto(ratings));
    }


    public CustomResponseDto<RatingResponseDto> getRatingById(long id) {
        return CustomResponseDto.Success(200, ratingDtoConverter.convertToRatingDto(getById(id)));
    }

    public CustomResponseDto<BestRatingResponseDto> getBestRatingByFoodRestaurantId(String foodName) {

        var food = foodService.getByName(foodName);

        List<FoodRestaurant> foodRestaurantList = foodRestaurantService.getByFoodId(food.getId());
        List<Long> listId = foodRestaurantList.stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Object[]> resultList = ratingRepostiory.getBestRatingByFoodRestaurantId(listId);

        Long foodRestaurantId = ((Number) resultList.get(0)[0]).longValue();
        Double averageRating = ((Number) resultList.get(0)[1]).doubleValue();
        Integer ratingCount = ((Number) resultList.get(0)[2]).intValue();

        FoodRestaurant foodRestaurant = foodRestaurantService.getById(foodRestaurantId);
        Restaurant restaurant = restaurantService.getById(foodRestaurant.getRestaurant().getId());
        BestRatingResponseDto bestRatingResponseDto = new BestRatingResponseDto(restaurant.getName(), foodName, averageRating,ratingCount);

        return CustomResponseDto.Success(200, bestRatingResponseDto);
    }


    public CustomResponseDto<RatingResponseDto> updateById(long id, String accessToken, UpdateRatingRequestDto updateRatingRequestDto) {

        var email = jwtService.getEmail(accessToken);
        var user = userService.getByEmail(email);
        var rating = checkUserRating(id, user.getId());

        rating.setRating(updateRatingRequestDto.rating());
        ratingRepostiory.save(rating);

        return CustomResponseDto.Success(200, ratingDtoConverter.convertToRatingDto(rating));
    }

    public void deleteById(long id, String accessToken) {
        var email = jwtService.getEmail(accessToken);
        var user = userService.getByEmail(email);
        var rating = checkUserRating(id, user.getId());

        ratingRepostiory.delete(rating);
    }


    private Rating checkUserRating(long id, String userId) {
        return ratingRepostiory.findByIdAndUserId(id, userId).orElseThrow(() -> new NotFoundException("Rating not found"));
    }


    public void deleteById(long id) {
        Rating category = getById(id);
        ratingRepostiory.delete(category);
    }


    public Rating getById(long id) {
        return ratingRepostiory.findById(id).orElseThrow(
                () -> new NotFoundException("Rating (" + id + ") not found"));
    }

    private Optional<Rating> existsRating(String userId, long foodRestaurantId) {
        return ratingRepostiory.findByUserIdAndFoodRestaurantId(userId, foodRestaurantId);
    }

}
