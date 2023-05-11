package com.spring.jwt.demo.dto.dtoConverter;

import com.spring.jwt.demo.dto.response.FoodResponseDto;
import com.spring.jwt.demo.dto.response.FoodWithRestaurantResponseDto;
import com.spring.jwt.demo.entity.Food;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FoodDtoConverter {

    public FoodResponseDto convertToFoodDto(Food food) {
        return new FoodResponseDto(food.getId(), food.getName(), food.getCategory().getName(),
                food.getCategory().getId(), food.getCreatedAt());
    }

    public List<FoodResponseDto> convertToFoodDto(List<Food> food) {

        return food.stream().map(x -> new FoodResponseDto(x.getId(), x.getName(), x.getCategory().getName(),
                        x.getCategory().getId(), x.getCreatedAt()))
                .collect(Collectors.toList());

    }


    public FoodWithRestaurantResponseDto convertToFoodRestaurantDto(Food food) {
        return new FoodWithRestaurantResponseDto(food.getId(), food.getName(), food.getCategory().getName(),
                food.getCategory().getId(),
                food.getFoodRestaurant().stream()
                        .filter(x -> x.getFood().getId() == food.getId()).map(x -> x.getRestaurant().getName()).findFirst().orElseGet(() -> ""),
                food.getFoodRestaurant().stream()
                        .filter(x -> x.getFood().getId() == food.getId()).map(x -> x.getRestaurant().getId()).findFirst().orElseGet(() -> 0L),
                food.getCreatedAt());
    }

    public List<FoodWithRestaurantResponseDto> convertToFoodRestaurantDto(List<Food> food) {

        return food.stream().map(x -> new FoodWithRestaurantResponseDto(x.getId(), x.getName(), x.getCategory().getName(), x.getCategory().getId(),
                        x.getFoodRestaurant().stream()
                                .filter(t -> t.getFood().getId() == x.getId()).map(z -> z.getRestaurant().getName()).findFirst().orElseGet(() -> ""),
                        x.getFoodRestaurant().stream()
                                .filter(t -> t.getFood().getId() == x.getId()).map(z -> z.getRestaurant().getId()).findFirst().orElseGet(() -> 0L),
                        x.getCreatedAt()))
                .collect(Collectors.toList());

    }

}
