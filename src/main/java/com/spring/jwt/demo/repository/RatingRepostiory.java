package com.spring.jwt.demo.repository;

import com.spring.jwt.demo.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RatingRepostiory extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserIdAndFoodRestaurantId(String userId, long foodRestaurantId);

    Optional<Rating> findByIdAndUserId(long id, String userId);

    Optional<Rating> findByFoodRestaurantId(long id);


//    @Query(nativeQuery = true,value = "SELECT x.foodRestaurant.id, AVG(x.rating) FROM Rating x " +
//            "WHERE x.foodRestaurant.id IN :test GROUP BY x.foodRestaurant.id ORDER BY AVG(x.rating) DESC")
//    List<Object[]> getBestRatingByFoodRestaurantId(@Param("test") List<Long> test);

    @Query(nativeQuery = true, value = "SELECT r.food_restaurant_id, AVG(r.rating),COUNT(r.id) FROM Rating r WHERE r.food_restaurant_id IN (:foodRestaurantIds) GROUP BY r.food_restaurant_id ORDER BY AVG(r.rating) DESC  LIMIT 1")
    List<Object[]> getBestRatingByFoodRestaurantId(@Param("foodRestaurantIds") List<Long> foodRestaurantIds);

}
