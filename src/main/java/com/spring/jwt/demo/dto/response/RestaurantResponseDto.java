package com.spring.jwt.demo.dto.response;

import java.time.ZonedDateTime;
import java.util.List;

public record RestaurantResponseDto(
        Long id,
        String name,
        String userId,
        List<FoodResponseDto> foodList,
        ZonedDateTime createdAt
) {
}
