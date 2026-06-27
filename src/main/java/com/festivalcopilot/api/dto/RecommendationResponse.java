package com.festivalcopilot.api.dto;

public record RecommendationResponse(Long eventId, String title, String category, double score, String reason) {
}