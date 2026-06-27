package com.festivalcopilot.api.controller;

import java.util.List;

import com.festivalcopilot.api.dto.RecommendationResponse;
import com.festivalcopilot.service.RecommendationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/events")
    public List<RecommendationResponse> recommendEvents(@RequestParam Long userId) {
        return recommendationService.recommendEvents(userId);
    }
}