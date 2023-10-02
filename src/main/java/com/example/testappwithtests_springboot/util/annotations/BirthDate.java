package com.example.testappwithtests_springboot.util.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateRightFormedValidator.class)
public @interface DateRightFormed {

    String message() default "Birth date must match pattern 'YYYY-MM-DD'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
