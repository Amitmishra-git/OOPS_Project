package com.festivalcopilot.api.dto;

import jakarta.validation.constraints.NotBlank;

public record VenueRequest(
        @NotBlank String name,
        String address,
        double latitude,
        double longitude,
        int capacity,
        String venueType) {
}