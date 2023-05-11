package com.spring.jwt.demo.repository;

import com.spring.jwt.demo.entity.FoodRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRestaurantRepostiory extends JpaRepository<FoodRestaurant, Long> {

    Optional<FoodRestaurant> findByFoodIdAndRestaurantId(long foodId, long restaurantId);

    boolean existsByFoodIdAndRestaurantId(long foodId, long restaurantId);

    Optional<List<FoodRestaurant>> findByFoodId(long foodId);
    Optional<List<FoodRestaurant>> findByRestaurantId(long restaurantId);
}
