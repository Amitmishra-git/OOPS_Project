package com.festivalcopilot.api.controller;

import com.festivalcopilot.api.dto.ScheduleResponse;
import com.festivalcopilot.service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/generate")
    public ScheduleResponse generateSchedule(@RequestParam Long userId) {
        return scheduleService.generateSchedule(userId);
    }

    @GetMapping("/{userId}")
    public ScheduleResponse getSchedule(@PathVariable Long userId) {
        return scheduleService.getSchedule(userId);
    }
}