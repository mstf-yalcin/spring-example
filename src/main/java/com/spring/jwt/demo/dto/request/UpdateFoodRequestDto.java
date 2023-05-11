package com.spring.jwt.demo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateFoodRequestDto(
        @NotBlank
        String name,
        @Min(1)
        Long categoryId
) {
}
