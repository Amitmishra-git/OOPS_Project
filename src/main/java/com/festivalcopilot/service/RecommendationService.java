package com.festivalcopilot.service;

import java.util.List;

import com.festivalcopilot.api.dto.RecommendationResponse;

public interface RecommendationService {

    List<RecommendationResponse> recommendEvents(Long userId);
}