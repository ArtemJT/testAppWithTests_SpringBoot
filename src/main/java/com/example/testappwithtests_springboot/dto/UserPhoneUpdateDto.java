package com.example.testappwithtests_springboot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@Builder
public record UserPhoneUpdateDto(
        @Positive
        Long id,

        @NotNull
        @Size(min = 2, max = 32, message = "Phone number must be between 2 and 32 characters long")
        String phoneNumber
) {
}
