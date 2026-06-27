package com.festivalcopilot.api.dto;

import java.math.BigDecimal;

public record AnalyticsOverviewResponse(Long activeUsers, Long eventsToday, Long locations, Long alerts,
        BigDecimal totalRevenue) {
}