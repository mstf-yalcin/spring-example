package com.spring.jwt.demo.repository;

import com.spring.jwt.demo.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    boolean existsByName(String name);
    Optional<Restaurant> findByIdAndUserId(long id, String userId);
}
