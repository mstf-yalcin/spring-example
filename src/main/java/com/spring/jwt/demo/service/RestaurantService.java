package com.spring.jwt.demo.service;

import com.spring.jwt.demo.dto.CustomResponseDto;
import com.spring.jwt.demo.dto.dtoConverter.RestaurantDtoConverter;
import com.spring.jwt.demo.dto.request.AddFoodRestaurantDto;
import com.spring.jwt.demo.dto.request.CreateRestaurantRequestDto;
import com.spring.jwt.demo.dto.request.UpdateRestaurantDto;
import com.spring.jwt.demo.dto.response.RestaurantResponseDto;
import com.spring.jwt.demo.entity.Food;
import com.spring.jwt.demo.entity.FoodRestaurant;
import com.spring.jwt.demo.entity.Restaurant;
import com.spring.jwt.demo.entity.User;
import com.spring.jwt.demo.exception.DuplicateFieldException;
import com.spring.jwt.demo.exception.NotFoundException;
import com.spring.jwt.demo.jwt.JwtService;
import com.spring.jwt.demo.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantDtoConverter restaurantDtoConverter;
    private final UserService userService;

    private final FoodService foodService;

    private final FoodRestaurantService foodRestaurantService;
    private final JwtService jwtService;

    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantDtoConverter restaurantDtoConverter, UserService userService, FoodService foodService, FoodRestaurantService foodRestaurantService, JwtService jwtService) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantDtoConverter = restaurantDtoConverter;
        this.userService = userService;
        this.foodService = foodService;
        this.foodRestaurantService = foodRestaurantService;
        this.jwtService = jwtService;
    }


    public CustomResponseDto<RestaurantResponseDto> createRestaurant(CreateRestaurantRequestDto createRestaurantRequestDto) {

        duplicateData(createRestaurantRequestDto.name());
        User user = userService.getById(createRestaurantRequestDto.userId());

        Restaurant restaurant = restaurantRepository.save(Restaurant.builder()
                .name(createRestaurantRequestDto.name())
                .user(user)
                .foodRestaurant(new HashSet<>()).build());
        return CustomResponseDto.Success(201, restaurantDtoConverter.convertToRestaurantDto(restaurant));
    }


    public CustomResponseDto<RestaurantResponseDto> addFood(AddFoodRestaurantDto addFoodRestaurantDto, String accessToken) {

        duplicateFoodRestaurant(addFoodRestaurantDto.foodId(), addFoodRestaurantDto.restaurantId());

        var email = jwtService.getEmail(accessToken);
        var user = userService.getByEmail(email);

        //Check user restaurants
        user.getRestaurant().stream().filter(x -> x.getId() == addFoodRestaurantDto.restaurantId()).findFirst()
                .orElseThrow(() -> new NotFoundException("Restaurant (" + addFoodRestaurantDto.restaurantId() + ") not found."));

        Food food = foodService.getById(addFoodRestaurantDto.foodId());
        Restaurant restaurant = getById(addFoodRestaurantDto.restaurantId());

        var foodRestaurant = foodRestaurantService.create(food, restaurant);
        food.setFoodRestaurant(Collections.singleton(foodRestaurant));

        return CustomResponseDto.Success(201, restaurantDtoConverter.convertToRestaurantDto(restaurant));
    }

    public CustomResponseDto<List<RestaurantResponseDto>> getAll() {
        return CustomResponseDto.Success(200, restaurantDtoConverter.convertToRestaurantDto(restaurantRepository.findAll()));
    }

    public CustomResponseDto<RestaurantResponseDto> getRestaurantById(long id) {
        return CustomResponseDto.Success(200, restaurantDtoConverter.convertToRestaurantDto(getById(id)));
    }


    public CustomResponseDto<RestaurantResponseDto> updateById(long id, String accessToken, UpdateRestaurantDto updateRestaurantDto) {
        duplicateData(updateRestaurantDto.name());
//        var restaurant = getById(id);
        var email = jwtService.getEmail(accessToken);
        var user = userService.getByEmail(email);
        Restaurant restaurant = checkUserRestaurant(id, user.getId());
        restaurant.setName(updateRestaurantDto.name());
        restaurant.setUser(user);
        restaurantRepository.save(restaurant);

        return CustomResponseDto.Success(200, restaurantDtoConverter.convertToRestaurantDto(restaurant));
    }


    public void deleteById(long id, String accessToken) {
        var email = jwtService.getEmail(accessToken);
        var user = userService.getByEmail(email);
        Restaurant restaurant = checkUserRestaurant(id, user.getId());
        restaurantRepository.delete(restaurant);
    }

    public Restaurant getById(long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant (" + id + ") not found"));
    }

    public void duplicateData(String name) {
        if (restaurantRepository.existsByName(name))
            throw new DuplicateFieldException("Restaurant '" + name + "' already exist.");
    }

    public void duplicateFoodRestaurant(long foodId, long restaurantId) {

        if (foodRestaurantService.existsByFoodIdAndRestaurantId(foodId, restaurantId))
            throw new DuplicateFieldException("Restaurant(" + restaurantId + ") or food(" + foodId + ") already exist.");
    }

    private Restaurant checkUserRestaurant(long id, String userId) {
        return restaurantRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new NotFoundException("Restaurant not found"));
    }


}
