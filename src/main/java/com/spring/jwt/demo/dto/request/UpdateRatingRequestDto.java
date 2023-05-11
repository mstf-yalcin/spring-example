package com.spring.jwt.demo.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateRatingRequestDto(
        @NotBlank
        String userId,
        @Min(1)
        long foodRestaurantId,
        @Min(0) @Max(100)
        int rating
) {
}
