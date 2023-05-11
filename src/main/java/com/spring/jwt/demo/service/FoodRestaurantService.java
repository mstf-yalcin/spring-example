package com.spring.jwt.demo.service;

import com.spring.jwt.demo.entity.Food;
import com.spring.jwt.demo.entity.FoodRestaurant;
import com.spring.jwt.demo.entity.Restaurant;
import com.spring.jwt.demo.exception.NotFoundException;
import com.spring.jwt.demo.repository.FoodRestaurantRepostiory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class FoodRestaurantService {

    private final FoodRestaurantRepostiory foodRestaurantRepostiory;

    public FoodRestaurantService(FoodRestaurantRepostiory foodRestaurantRepostiory) {
        this.foodRestaurantRepostiory = foodRestaurantRepostiory;
    }


    public FoodRestaurant create(Food food, Restaurant restaurant) {
        return foodRestaurantRepostiory.save(FoodRestaurant.builder().food(food).restaurant(restaurant).ratings(new HashSet<>()).build());
    }

    public FoodRestaurant getById(long id) {
        return foodRestaurantRepostiory.findById(id)
                .orElseThrow(() -> new NotFoundException("FoodRestaurant (" + id + ") not found"));
    }

    public FoodRestaurant getByFoodIdAndRestaurantId(long foodId, long restaurantId) {
        return foodRestaurantRepostiory.findByFoodIdAndRestaurantId(foodId, restaurantId)
                .orElseThrow(() -> new NotFoundException("FoodRestaurant foodId(" + foodId + ") restaurantId(" + restaurantId + ") not found"));
    }

    public boolean existsByFoodIdAndRestaurantId(long foodId, long restaurantId) {
        return foodRestaurantRepostiory.existsByFoodIdAndRestaurantId(foodId, restaurantId);
    }

    public List<FoodRestaurant> getByFoodId(long foodId) {
        return foodRestaurantRepostiory.findByFoodId(foodId)
                .orElseThrow(() -> new NotFoundException("FoodRestaurant foodId(" + foodId + ") not found"));
    }

    public List<FoodRestaurant> getByRestaurantId(long restaurantId) {
        return foodRestaurantRepostiory.findByRestaurantId(restaurantId)
                .orElseThrow(() -> new NotFoundException("FoodRestaurant restaurantId(" + restaurantId + ") not found"));
    }

    public void deleteById(long id) {
        foodRestaurantRepostiory.deleteById(id);
    }

    public void deleteAll(List<FoodRestaurant> entities) {
        foodRestaurantRepostiory.deleteAll(entities);
    }
}
