package com.spring.jwt.demo.repository;

import com.spring.jwt.demo.entity.Enum_Role;
import com.spring.jwt.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByRole(Enum_Role roleName);
    boolean existsByRole(Enum_Role roleName);
}
