package com.festivalcopilot.api.dto;

public record UserResponse(Long id, String fullName, String email, String preferredGenres, String budgetPreference) {
}