package com.spring.jwt.demo.dto.response;

public record RatingResponseDto(
        String userId,
        int rating,
        FoodWithRestaurantResponseDto food
) {
}
