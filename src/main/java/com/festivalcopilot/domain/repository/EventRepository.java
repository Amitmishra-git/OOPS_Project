package com.festivalcopilot.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivalcopilot.domain.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByCategoryIgnoreCase(String category);
}