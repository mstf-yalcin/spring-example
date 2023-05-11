package com.spring.jwt.demo.dto.dtoConverter;

import com.spring.jwt.demo.dto.response.CategoryResponseDto;
import com.spring.jwt.demo.dto.response.CategoryWithFoodDto;
import com.spring.jwt.demo.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryDtoConverter {


    private FoodDtoConverter foodConverter;

    public CategoryDtoConverter(FoodDtoConverter foodConverter) {
        this.foodConverter = foodConverter;
    }

    public CategoryResponseDto convertToCategoryDto(Category from) {
        return new CategoryResponseDto(from.getId(), from.getName(), from.getCreatedAt());
    }

    public List<CategoryResponseDto> convertToCategoryDto(List<Category> from) {
        return from.stream().map(x -> new CategoryResponseDto(x.getId(), x.getName(), x.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public List<CategoryWithFoodDto> convertToCategoryFoodDto(List<Category> from) {
        return from.stream().map(x -> new CategoryWithFoodDto(x.getId(), x.getName(), x.getCreatedAt(),
                        foodConverter.convertToFoodDto(x.getFoods())))
                .collect(Collectors.toList());
    }

}
