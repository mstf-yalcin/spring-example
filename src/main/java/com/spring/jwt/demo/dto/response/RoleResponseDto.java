package com.spring.jwt.demo.dto.response;

import com.spring.jwt.demo.entity.Enum_Role;

public record RoleResponseDto(
        String id,
        Enum_Role name
) {
}
