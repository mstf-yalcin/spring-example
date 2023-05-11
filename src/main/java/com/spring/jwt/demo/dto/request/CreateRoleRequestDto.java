package com.spring.jwt.demo.dto.request;

import com.spring.jwt.demo.entity.Enum_Role;

public record CreateRoleRequestDto(
        Enum_Role role
) {
}
