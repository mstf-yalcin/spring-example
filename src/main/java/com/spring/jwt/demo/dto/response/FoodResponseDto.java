package com.spring.jwt.demo.dto.response;

import java.time.ZonedDateTime;

public record FoodResponseDto(

        Long id,
        String name,
        String categoryName,
        Long categoryId,
        ZonedDateTime createdAt
) {
}
