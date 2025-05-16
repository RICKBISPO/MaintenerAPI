package com.simples.maintainer.validation.validators;

import com.simples.maintainer.validation.annotations.OptionalPastOrPresent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class OptionalPastOrPresentValidator implements ConstraintValidator<OptionalPastOrPresent, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return !value.isAfter(LocalDate.now());
    }
}

