package com.spring.jwt.demo.dto.response;

import com.spring.jwt.demo.entity.Enum_Role;

import java.util.List;

public record UserRoleDto(
        List<Enum_Role> role,
        String userId
) {
}
