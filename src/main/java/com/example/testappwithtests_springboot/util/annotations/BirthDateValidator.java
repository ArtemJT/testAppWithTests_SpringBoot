package com.example.testappwithtests_springboot.util.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

/**
 * @author Artem Kovalov on 01.10.2023
 */
public final class DateRightFormedValidator implements ConstraintValidator<DateRightFormed, String> {

    private final LocalDate minYear = LocalDate.now().minusYears(18);


    @Override
    public void initialize(DateRightFormed constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            LocalDate date = LocalDate.parse(value);
            return date.isBefore(minYear);
        }
        return false;
    }
}
