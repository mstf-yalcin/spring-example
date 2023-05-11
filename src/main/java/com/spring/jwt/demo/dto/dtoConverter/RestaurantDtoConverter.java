package com.spring.jwt.demo.dto.dtoConverter;

import com.spring.jwt.demo.dto.response.FoodWithRestaurantResponseDto;
import com.spring.jwt.demo.dto.response.RestaurantResponseDto;
import com.spring.jwt.demo.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantDtoConverter {

    private final FoodDtoConverter foodDtoConverter;

    public RestaurantDtoConverter(FoodDtoConverter foodDtoConverter) {
        this.foodDtoConverter = foodDtoConverter;
    }

    public RestaurantResponseDto convertToRestaurantDto(Restaurant from) {
        return new RestaurantResponseDto(from.getId(), from.getName(), from.getUser().getId(),
                foodDtoConverter.convertToFoodDto(from.getFoodRestaurant().stream()
                        .map(x -> x.getFood()).collect(Collectors.toList())), from.getCreatedAt());
    }

    public List<RestaurantResponseDto> convertToRestaurantDto(List<Restaurant> from) {

        return from.stream().map(x -> new RestaurantResponseDto(x.getId(), x.getName(), x.getUser().getId(),
                        foodDtoConverter.convertToFoodDto(x.getFoodRestaurant()
                                .stream().map(t -> t.getFood()).collect(Collectors.toList())), x.getCreatedAt()))
                .collect(Collectors.toList());
    }


}
