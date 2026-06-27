package com.festivalcopilot.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank String fullName,
        @Email @NotBlank String email,
        @NotBlank String passwordHash,
        String preferredGenres,
        String budgetPreference) {
}