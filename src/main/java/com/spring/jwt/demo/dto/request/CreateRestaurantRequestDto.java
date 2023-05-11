package com.spring.jwt.demo.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateRestaurantRequestDto(
        @NotBlank
        String name,
        @NotBlank
        String userId
) {
}
