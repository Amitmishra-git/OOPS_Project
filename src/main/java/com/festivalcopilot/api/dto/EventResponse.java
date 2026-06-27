package com.festivalcopilot.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventResponse(Long id, String title, String description, String category, LocalDateTime startTime,
        LocalDateTime endTime, BigDecimal price, double score, VenueResponse venue) {
}