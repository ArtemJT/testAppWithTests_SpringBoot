package com.example.testappwithtests_springboot.dto;

import com.example.testappwithtests_springboot.util.annotations.BirthDate;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@Builder
public record UserDto(
        @Positive
        Long id,

        @NotNull
        @NotBlank
        @Size(min = 2, max = 32, message = "First name must be between 2 and 32 characters long")
        String firstName,

        @NotNull
        @NotBlank
        @Size(min = 2, max = 32, message = "Last name must be between 2 and 32 characters long")
        String lastName,

        @NotNull
        @NotBlank
        @Email
        String email,

        @NotNull
        @DateTimeFormat(pattern = "'YYYY-MM-DD'")
        @BirthDate
        LocalDate birthDate,

        String address,

        String phoneNumber,

        Boolean isDeleted
) {
}
