package com.spring.jwt.demo.repository;

import com.spring.jwt.demo.entity.Enum_Role;
import com.spring.jwt.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByRole(Enum_Role roleName);
}
