package com.simples.maintainer.validation.validators;

import com.simples.maintainer.validation.annotations.OptionalFutureOrPresent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class OptionalFutureOrPresentValidator implements ConstraintValidator<OptionalFutureOrPresent, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return !value.isBefore(LocalDate.now());
    }
}

