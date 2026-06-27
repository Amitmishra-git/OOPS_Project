package com.festivalcopilot.service;

import com.festivalcopilot.api.dto.ScheduleResponse;

public interface ScheduleService {

    ScheduleResponse generateSchedule(Long userId);

    ScheduleResponse getSchedule(Long userId);
}