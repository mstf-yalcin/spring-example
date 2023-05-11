package com.spring.jwt.demo.repository;

import com.spring.jwt.demo.entity.Enum_Role;
import com.spring.jwt.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
