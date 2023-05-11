package com.spring.jwt.demo.controller;

import com.spring.jwt.demo.dto.CustomResponseDto;
import com.spring.jwt.demo.dto.request.CreateCategoryRequestDto;
import com.spring.jwt.demo.dto.request.UpdateCategoryRequestDto;
import com.spring.jwt.demo.dto.response.CategoryResponseDto;
import com.spring.jwt.demo.dto.response.CategoryWithFoodDto;
import com.spring.jwt.demo.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@Validated
public class CategoryController  {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CustomResponseDto<CategoryResponseDto>> create(@Valid @RequestBody CreateCategoryRequestDto createCategoryRequestDto) {

        return ResponseEntity.created(URI.create("/api/v1/category"))
                .body(categoryService.createCategory(createCategoryRequestDto));
    }

    @GetMapping("/getAllWithFood")
    @SecurityRequirements(value = {})
    public ResponseEntity<CustomResponseDto<List<CategoryWithFoodDto>>> getAllCategoriesWithFood() {
        return ResponseEntity.ok(categoryService.getAllCategoryFood());
    }
    @GetMapping
    @SecurityRequirements(value = {})
    public ResponseEntity<CustomResponseDto<List<CategoryResponseDto>>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("{id}")
    @SecurityRequirements(value = {})
    public ResponseEntity<CustomResponseDto<CategoryResponseDto>> getById(@PathVariable @Positive long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomResponseDto<CategoryResponseDto>> updateById(@PathVariable @Positive long id,
                                                                              @Valid @RequestBody UpdateCategoryRequestDto updateCategoryRequestDto) {
        return ResponseEntity.ok(categoryService.updateById(id, updateCategoryRequestDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Positive long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
