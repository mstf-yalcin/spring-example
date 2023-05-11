package com.spring.jwt.demo.controller;

import com.spring.jwt.demo.dto.CustomResponseDto;
import com.spring.jwt.demo.dto.request.CreateRoleRequestDto;
import com.spring.jwt.demo.dto.request.SetRoleRequestDto;
import com.spring.jwt.demo.dto.response.RoleResponseDto;
import com.spring.jwt.demo.dto.response.UserRoleDto;
import com.spring.jwt.demo.service.RoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<CustomResponseDto<RoleResponseDto>> createRole(@Valid @RequestBody CreateRoleRequestDto createRoleRequestDto) {

        return ResponseEntity.created(URI.create("/api/v1/role"))
                .body(roleService.createRole(createRoleRequestDto.role()));
    }

    @PostMapping("/setRole")
    public ResponseEntity<CustomResponseDto<UserRoleDto>> setRole(@Valid @RequestBody SetRoleRequestDto setRoleRequestDto) {

        return ResponseEntity.created(URI.create("/api/v1/role"))
                .body(roleService.setRole(setRoleRequestDto.roleId(), setRoleRequestDto.userId()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @NotBlank String id) {
        roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
