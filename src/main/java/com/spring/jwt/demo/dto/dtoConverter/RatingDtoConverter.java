package com.spring.jwt.demo.dto.dtoConverter;

import com.spring.jwt.demo.dto.response.RatingResponseDto;
import com.spring.jwt.demo.entity.Rating;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RatingDtoConverter {


    private final FoodDtoConverter foodDtoConverter;

    public RatingDtoConverter(FoodDtoConverter foodDtoConverter) {
        this.foodDtoConverter = foodDtoConverter;
    }

    public RatingResponseDto convertToRatingDto(Rating from) {
        return new RatingResponseDto(from.getUser().getId(), from.getRating(),
                foodDtoConverter.convertToFoodRestaurantDto(from.getFoodRestaurant().getFood()));
    }


    public List<RatingResponseDto> convertToRatingDto(List<Rating> from) {

        return from.stream().map(x -> new RatingResponseDto(x.getUser().getId(), x.getRating(),
                foodDtoConverter.convertToFoodRestaurantDto(x.getFoodRestaurant().getFood()))).collect(Collectors.toList());

    }

}
