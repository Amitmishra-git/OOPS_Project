package com.festivalcopilot.api.controller;

import com.festivalcopilot.api.dto.AnalyticsOverviewResponse;
import com.festivalcopilot.service.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/overview")
    public AnalyticsOverviewResponse getOverview() {
        return analyticsService.getOverview();
    }
}