package com.simples.maintainer.validation.validators;

import com.simples.maintainer.validation.annotations.OptionalPositive;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OptionalPositiveValidator implements ConstraintValidator<OptionalPositive, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value > 0;
    }
}

