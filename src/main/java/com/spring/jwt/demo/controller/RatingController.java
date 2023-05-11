package com.spring.jwt.demo.controller;

import com.spring.jwt.demo.dto.CustomResponseDto;
import com.spring.jwt.demo.dto.request.*;
import com.spring.jwt.demo.dto.response.BestRatingResponseDto;
import com.spring.jwt.demo.dto.response.RatingResponseDto;
import com.spring.jwt.demo.service.RatingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rating")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<CustomResponseDto<RatingResponseDto>> create(@Valid @RequestBody CreateRatingRequestDto createRatingRequestDto) {

        return ResponseEntity.created(URI.create("/api/v1/rating"))
                .body(ratingService.createRating(createRatingRequestDto));
    }

    @GetMapping
    @SecurityRequirements(value = {})
    public ResponseEntity<CustomResponseDto<List<RatingResponseDto>>> getAll() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("{foodId}/{restaurantId}")
    @SecurityRequirements(value = {})
    public ResponseEntity<CustomResponseDto<List<RatingResponseDto>>> getAllRatingsByFoodId(@PathVariable @Positive long foodId,
                                                                                            @PathVariable @Positive long restaurantId) {
        return ResponseEntity.ok(ratingService.getAllRatingsByFoodId(foodId, restaurantId));
    }


    @GetMapping("{id}")
    @SecurityRequirements(value = {})
    public ResponseEntity<CustomResponseDto<RatingResponseDto>> getById(@PathVariable @Positive long id) {
        return ResponseEntity.ok(ratingService.getRatingById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomResponseDto<RatingResponseDto>> updateById(@PathVariable @Positive long id,
                                                                           @RequestHeader("Bearer") String accessToken,
                                                                           @Valid @RequestBody UpdateRatingRequestDto updateRatingRequestDto) {
        return ResponseEntity.ok(ratingService.updateById(id, accessToken, updateRatingRequestDto));
    }


    @GetMapping("getBestRatingByFood/{foodName}")
    @SecurityRequirements(value = {})
    public ResponseEntity<CustomResponseDto<BestRatingResponseDto>> getBestRatingByFood(@PathVariable String foodName) {
        return ResponseEntity.ok(ratingService.getBestRatingByFoodRestaurantId(foodName));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Positive long id, @RequestHeader("Bearer") String accessToken) {
        ratingService.deleteById(id, accessToken);
        return ResponseEntity.noContent().build();
    }

}
