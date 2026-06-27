package com.festivalcopilot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivalcopilot.domain.entity.Venue;

public interface VenueRepository extends JpaRepository<Venue, Long> {
}