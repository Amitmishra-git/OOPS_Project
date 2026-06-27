package com.festivalcopilot.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivalcopilot.domain.entity.ScheduleItem;

public interface ScheduleItemRepository extends JpaRepository<ScheduleItem, Long> {

    List<ScheduleItem> findByScheduleIdOrderByDisplayOrderAsc(Long scheduleId);
}