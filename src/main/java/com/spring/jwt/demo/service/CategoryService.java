package com.spring.jwt.demo.service;

import com.spring.jwt.demo.dto.CustomResponseDto;
import com.spring.jwt.demo.dto.request.CreateCategoryRequestDto;
import com.spring.jwt.demo.dto.request.UpdateCategoryRequestDto;
import com.spring.jwt.demo.dto.response.CategoryResponseDto;
import com.spring.jwt.demo.dto.dtoConverter.CategoryDtoConverter;
import com.spring.jwt.demo.dto.response.CategoryWithFoodDto;
import com.spring.jwt.demo.entity.Category;
import com.spring.jwt.demo.exception.NotFoundException;
import com.spring.jwt.demo.jwt.JwtService;
import com.spring.jwt.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter categoryDtoConverter;

    private final JwtService jwtService;


    public CategoryService(CategoryRepository categoryRepository,
                           CategoryDtoConverter categoryDtoConverter,
                           JwtService jwtService) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoConverter = categoryDtoConverter;
        this.jwtService = jwtService;
    }


    public CustomResponseDto<CategoryResponseDto> createCategory(CreateCategoryRequestDto createCategoryRequestDto) {

        Category category = categoryRepository.save(Category.builder()
                .name(createCategoryRequestDto.name()).build());
        return CustomResponseDto.Success(201, categoryDtoConverter.convertToCategoryDto(category));
    }


    public CustomResponseDto<List<CategoryResponseDto>> getAllCategory() {
        List<Category> category = categoryRepository.findAll();
        return CustomResponseDto.Success(200, categoryDtoConverter.convertToCategoryDto(category));
    }

    public CustomResponseDto<List<CategoryWithFoodDto>> getAllCategoryFood() {
        List<Category> category = categoryRepository.findAll();
        return CustomResponseDto.Success(200, categoryDtoConverter.convertToCategoryFoodDto(category));
    }

    public CustomResponseDto<CategoryResponseDto> getCategoryById(long id) {
        return CustomResponseDto.Success(200, categoryDtoConverter.convertToCategoryDto(getById(id)));
    }

    public Category getById(long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Category (" + id + ") not found"));
    }

    public CustomResponseDto<CategoryResponseDto> updateById(long id, UpdateCategoryRequestDto updateCategoryRequestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Category (" + id + ") not found"));
        category.setName(updateCategoryRequestDto.name());
        categoryRepository.save(category);
        return CustomResponseDto.Success(200, categoryDtoConverter.convertToCategoryDto(category));
    }

    public void deleteById(long id) {
        Category category = getById(id);
        categoryRepository.delete(category);
    }

    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

}
