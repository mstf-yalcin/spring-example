package com.spring.jwt.demo.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequestDto(
        @NotBlank
        String name
) {
}
