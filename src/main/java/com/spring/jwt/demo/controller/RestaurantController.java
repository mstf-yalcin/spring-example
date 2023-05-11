package com.spring.jwt.demo.controller;

import com.spring.jwt.demo.dto.CustomResponseDto;
import com.spring.jwt.demo.dto.request.AddFoodRestaurantDto;
import com.spring.jwt.demo.dto.request.CreateRestaurantRequestDto;
import com.spring.jwt.demo.dto.request.UpdateRestaurantDto;
import com.spring.jwt.demo.dto.response.RestaurantResponseDto;
import com.spring.jwt.demo.service.RestaurantService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    public ResponseEntity<CustomResponseDto<RestaurantResponseDto>> create(@Valid @RequestBody CreateRestaurantRequestDto createRestaurantRequestDto) {

        return ResponseEntity.created(URI.create("/api/v1/restaurant"))
                .body(restaurantService.createRestaurant(createRestaurantRequestDto));
    }

    @PostMapping("/addFoodRestaurant")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    public ResponseEntity<CustomResponseDto<RestaurantResponseDto>> addFoodRestaurant(@Valid @RequestBody AddFoodRestaurantDto addFoodRestaurantDto,
                                                                                      @RequestHeader("Bearer") String accessToken) {

        return ResponseEntity.created(URI.create("/api/v1/addFoodRestaurant"))
                .body(restaurantService.addFood(addFoodRestaurantDto,accessToken));
    }

    @GetMapping
    @SecurityRequirements(value = {})
    public ResponseEntity<CustomResponseDto<List<RestaurantResponseDto>>> getAll() {
        return ResponseEntity.ok(restaurantService.getAll());
    }

    @GetMapping("{id}")
    @SecurityRequirements(value = {})
    public ResponseEntity<CustomResponseDto<RestaurantResponseDto>> getById(@PathVariable @Positive long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }


    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    public ResponseEntity<CustomResponseDto<RestaurantResponseDto>> updateById(@PathVariable @Positive long id,
                                                                               @RequestHeader("Bearer") String accessToken,
                                                                               @Valid @RequestBody UpdateRestaurantDto updateRestaurantDto) {
        return ResponseEntity.ok(restaurantService.updateById(id, accessToken, updateRestaurantDto));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable @Positive long id, @RequestHeader("Bearer") String accessToken) {
        restaurantService.deleteById(id, accessToken);
        return ResponseEntity.noContent().build();
    }
}
