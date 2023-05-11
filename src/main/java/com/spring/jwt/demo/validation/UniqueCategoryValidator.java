package com.spring.jwt.demo.validation;

import com.spring.jwt.demo.service.CategoryService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueCategoryValidator implements ConstraintValidator<UniqueCategory, String> {
    private final CategoryService categoryService;
    public UniqueCategoryValidator(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return !categoryService.existsByName(name);
    }
}
