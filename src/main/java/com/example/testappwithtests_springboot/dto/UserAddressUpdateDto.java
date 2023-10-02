package com.example.testappwithtests_springboot.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@Builder
public record UserDto(
        @Positive
        Long id,

        @NotNull
        @Size(min = 2, max = 32, message = "First name must be between 2 and 32 characters long")
        String firstName,

        @NotNull
        @Size(min = 2, max = 32, message = "Last name must be between 2 and 32 characters long")
        String lastName,

        @Email
        @NotNull
        String email,

        @NotNull
        @Size(min = 10, message = "Birth date must match pattern 'YYYY-MM-DD'")
        LocalDate birthDate,

        String address,

        String phoneNumber
) {
}
