package com.spring.jwt.demo.dto.response;

import lombok.*;

import java.time.ZonedDateTime;
public record CategoryResponseDto(

        long id,
        String name,
        ZonedDateTime createdAt
) {
}
