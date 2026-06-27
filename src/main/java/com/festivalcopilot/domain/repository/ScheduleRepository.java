package com.festivalcopilot.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivalcopilot.domain.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findByUserId(Long userId);
}