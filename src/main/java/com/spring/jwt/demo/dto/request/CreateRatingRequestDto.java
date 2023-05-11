package com.spring.jwt.demo.dto.request;

import com.spring.jwt.demo.entity.FoodRestaurant;
import com.spring.jwt.demo.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateRatingRequestDto(
        @NotBlank
        String userId,
        @Min(1)
        long foodRestaurantId,
        @Min(0) @Max(100)
        int rating
) {
}
