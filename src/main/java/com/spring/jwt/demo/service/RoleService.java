package com.spring.jwt.demo.service;

import com.spring.jwt.demo.dto.CustomResponseDto;
import com.spring.jwt.demo.dto.response.RoleResponseDto;
import com.spring.jwt.demo.dto.response.UserRoleDto;
import com.spring.jwt.demo.entity.Enum_Role;
import com.spring.jwt.demo.entity.Restaurant;
import com.spring.jwt.demo.entity.Role;
import com.spring.jwt.demo.entity.User;
import com.spring.jwt.demo.exception.DuplicateFieldException;
import com.spring.jwt.demo.exception.NotFoundException;
import com.spring.jwt.demo.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserService userService;

    public RoleService(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }


    public CustomResponseDto<RoleResponseDto> createRole(Enum_Role roleName) {
        if (existsByRoleName(roleName))
            throw new NotFoundException("Role name(" + roleName + ") already exists.");

        var role = roleRepository.save(Role.builder().role(roleName).build());
        return CustomResponseDto.Success(201, new RoleResponseDto(role.getId(), role.getRole()));
    }


    public CustomResponseDto<UserRoleDto> setRole(String roleId, String userId) {
        var user = userService.getById(userId);
        var role = getById(roleId);
        if (user.getRoleList().stream().filter(x -> x.getRole() == role.getRole()).findFirst().isPresent())
            throw new DuplicateFieldException("Role (" + roleId + ") is already exists in user (userId)");

        user.getRoleList().add(role);
        userService.updateUser(user);

        return CustomResponseDto.Success(201, new UserRoleDto(user.getRoleList().stream().map(x -> x.getRole()).collect(Collectors.toList()), user.getId()));
    }

    public void deleteById(String id) {
        var role = getById(id);
        roleRepository.delete(role);
    }

    private void deleteByName(Enum_Role roleName) {
        var role = getRole(roleName);
        roleRepository.delete(role);
    }

    private Role getById(String roleId) {
        return roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role (" + roleId + ") not found."));
    }

    public Role getRole(Enum_Role roleName) {
        return roleRepository.findByRole(roleName).orElseThrow(() -> new NotFoundException("Role name(" + roleName + ") not found."));
    }

    public boolean existsByRoleName(Enum_Role roleName) {
        return roleRepository.existsByRole(roleName);
    }

}
