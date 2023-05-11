package com.spring.jwt.demo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateRestaurantDto(
        @NotBlank
        String name,
        @NotBlank
        String userId
) {
}
