package com.festivalcopilot.api.dto;

import java.time.LocalDateTime;

public record ScheduleItemResponse(Long eventId, String title, LocalDateTime startTime, LocalDateTime endTime,
        int displayOrder) {
}