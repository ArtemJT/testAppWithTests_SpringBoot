package com.example.testappwithtests_springboot.dto;

import jakarta.validation.constraints.Positive;
import lombok.Builder;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@Builder
public record UserIdDto(@Positive Long id) {
}
