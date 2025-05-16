package com.simples.maintainer.validation.annotations;

import com.simples.maintainer.validation.validators.OptionalPositiveValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OptionalPositiveValidator.class)
public @interface OptionalPositive {
    String message() default "integer must be greater than zero";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

