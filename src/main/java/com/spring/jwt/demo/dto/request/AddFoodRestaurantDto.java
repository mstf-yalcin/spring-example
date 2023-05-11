package com.spring.jwt.demo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AddFoodRestaurantDto(

        @Min(1)
        long restaurantId,
        @Min(1)
        long foodId
) {
}
