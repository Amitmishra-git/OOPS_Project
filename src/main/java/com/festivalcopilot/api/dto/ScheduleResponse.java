package com.festivalcopilot.api.dto;

import java.util.List;

public record ScheduleResponse(Long scheduleId, Long userId, String title, List<ScheduleItemResponse> items) {
}