package com.spring.jwt.demo.controller;

import com.spring.jwt.demo.dto.CustomResponseDto;
import com.spring.jwt.demo.dto.request.CreateFoodRequestDto;
import com.spring.jwt.demo.dto.request.UpdateFoodRequestDto;
import com.spring.jwt.demo.dto.response.FoodWithRestaurantResponseDto;
import com.spring.jwt.demo.service.FoodService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/food")
public class FoodController {

    private FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping

    public ResponseEntity<CustomResponseDto<FoodWithRestaurantResponseDto>> create(@Valid @RequestBody CreateFoodRequestDto createFoodRequestDto) {

        return ResponseEntity.created(URI.create("/api/v1/food"))
                .body(foodService.createFood(createFoodRequestDto));
    }

    @GetMapping
    @SecurityRequirements(value = {})
    public ResponseEntity<CustomResponseDto<List<FoodWithRestaurantResponseDto>>> getAll() {
        return ResponseEntity.ok(foodService.getAll());
    }

    @GetMapping("{id}")
    @SecurityRequirements(value = {})
    public ResponseEntity<CustomResponseDto<FoodWithRestaurantResponseDto>> getById(@PathVariable @Positive long id) {
        return ResponseEntity.ok(foodService.getFoodById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomResponseDto<FoodWithRestaurantResponseDto>> updateById(@PathVariable @Positive long id,
                                                                                        @Valid @RequestBody UpdateFoodRequestDto updateFoodRequestDto) {
        return ResponseEntity.ok(foodService.updateById(id, updateFoodRequestDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Positive long id) {
        foodService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
