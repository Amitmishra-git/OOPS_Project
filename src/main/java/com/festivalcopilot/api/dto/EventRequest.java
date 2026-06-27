package com.festivalcopilot.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventRequest(
        @NotBlank String title,
        String description,
        @NotBlank String category,
        @NotNull LocalDateTime startTime,
        @NotNull LocalDateTime endTime,
        BigDecimal price,
        Double score,
        Long venueId) {
}