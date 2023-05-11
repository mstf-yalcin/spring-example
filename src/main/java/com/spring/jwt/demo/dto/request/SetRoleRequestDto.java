package com.spring.jwt.demo.dto.request;

public record SetRoleRequestDto(
        String roleId,
        String userId
) {
}
