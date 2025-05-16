package com.simples.maintainer.validation.annotations;

import com.simples.maintainer.validation.validators.OptionalFutureOrPresentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OptionalFutureOrPresentValidator.class)
public @interface OptionalFutureOrPresent {
    String message() default "date must be in the present or future";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

