package com.spring.jwt.demo.service;

import com.spring.jwt.demo.dto.CustomResponseDto;
import com.spring.jwt.demo.dto.dtoConverter.FoodDtoConverter;
import com.spring.jwt.demo.dto.request.CreateFoodRequestDto;
import com.spring.jwt.demo.dto.request.UpdateFoodRequestDto;
import com.spring.jwt.demo.dto.response.FoodWithRestaurantResponseDto;
import com.spring.jwt.demo.entity.Category;
import com.spring.jwt.demo.entity.Food;
import com.spring.jwt.demo.exception.DuplicateFieldException;
import com.spring.jwt.demo.exception.NotFoundException;
import com.spring.jwt.demo.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final CategoryService categoryService;


    private final FoodDtoConverter converter;

    public FoodService(FoodRepository foodRepository, CategoryService categoryService, FoodDtoConverter converter) {
        this.foodRepository = foodRepository;
        this.categoryService = categoryService;
        this.converter = converter;
    }

    public CustomResponseDto<FoodWithRestaurantResponseDto> createFood(CreateFoodRequestDto createFoodRequestDto) {

        duplicateName(createFoodRequestDto.name());
        Category category = categoryService.getById(createFoodRequestDto.categoryId());
//        Restaurant restaurant = restaurantService.getById(createFoodRequestDto.restaurantId());

        Food food = foodRepository.save(Food.builder().name(createFoodRequestDto.name()).category(category)
                .foodRestaurant(new HashSet<>()).build());

//        var foodRestaurant= foodRestaurantService.create(food, restaurant);
//        food.setFoodRestaurant(Collections.singleton(foodRestaurant));
        return CustomResponseDto.Success(201, converter.convertToFoodRestaurantDto(food));
    }

    public CustomResponseDto<FoodWithRestaurantResponseDto> getFoodById(long id) {
        return CustomResponseDto.Success(200, converter.convertToFoodRestaurantDto(getById(id)));
    }


    public CustomResponseDto<List<FoodWithRestaurantResponseDto>> getAll() {
        return CustomResponseDto.Success(200, converter.convertToFoodRestaurantDto(foodRepository.findAll()));
    }


    public CustomResponseDto<FoodWithRestaurantResponseDto> updateById(long id, UpdateFoodRequestDto updateFoodRequestDto) {
        Food food = getById(id);
        Category category = categoryService.getById(updateFoodRequestDto.categoryId());
        food.setCategory(category);
        return CustomResponseDto.Success(200, converter.convertToFoodRestaurantDto(foodRepository.save(food)));
    }


    public void duplicateName(String name) {
        if (foodRepository.existsByName(name))
            throw new DuplicateFieldException("Food ('" + name + "') already exist.");
    }

    public void deleteById(long id) {
        Food food = getById(id);
        foodRepository.delete(food);
    }

    public Food getById(long id) {
        return foodRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Food (" + id + ") not found"));
    }

    public Food getByName(String name) {
        return foodRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Food (" + name + ") not found"));
    }

}
