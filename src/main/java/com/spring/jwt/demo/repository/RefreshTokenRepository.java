package com.spring.jwt.demo.repository;

import com.spring.jwt.demo.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByTokenAndId(String token, Long id);
    Optional<RefreshToken> findByToken(String token);
}
