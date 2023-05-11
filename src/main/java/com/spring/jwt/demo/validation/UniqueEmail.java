package com.spring.jwt.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {

    String message() default "{validation.constraints.UniqueEmail.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

