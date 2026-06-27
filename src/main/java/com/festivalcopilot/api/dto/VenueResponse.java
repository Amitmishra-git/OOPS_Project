package com.festivalcopilot.api.dto;

public record VenueResponse(Long id, String name, String address, double latitude, double longitude, int capacity,
        String venueType) {
}