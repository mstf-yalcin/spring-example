package com.spring.jwt.demo.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueCategoryValidator.class)
public @interface UniqueCategory {

    String message() default "{validation.constraints.UniqueCategory.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
