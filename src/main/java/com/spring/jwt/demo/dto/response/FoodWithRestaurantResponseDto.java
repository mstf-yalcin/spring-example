package com.spring.jwt.demo.dto.response;

import java.time.ZonedDateTime;


public record FoodWithRestaurantResponseDto(

        Long id,
        String name,
        String categoryName,
        Long categoryId,
        String restaurantName,

        Long restaurantId,
        ZonedDateTime createdAt
) {
}