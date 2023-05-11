package com.spring.jwt.demo.dto.response;

public record BestRatingResponseDto(
        String restaurantName,
        String foodName,
        double rating,
        int userCount
) {
}
