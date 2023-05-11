package com.spring.jwt.demo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateFoodRequestDto(
        @NotBlank
        String name,

        @Min(1)
        Long categoryId

) {
}
