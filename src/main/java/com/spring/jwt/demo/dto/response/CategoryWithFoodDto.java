package com.spring.jwt.demo.dto.response;

import java.time.ZonedDateTime;
import java.util.List;

public record CategoryWithFoodDto(
        long id,
        String name,
        ZonedDateTime createdAt,
        List<FoodResponseDto> food
) {
}
