package com.spring.jwt.demo.dto.request;

import com.spring.jwt.demo.validation.UniqueCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


public record CreateCategoryRequestDto(
        @NotBlank
        @UniqueCategory
        String name
) {
}
