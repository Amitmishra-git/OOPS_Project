package com.festivalcopilot.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivalcopilot.domain.entity.CrowdSnapshot;

public interface CrowdSnapshotRepository extends JpaRepository<CrowdSnapshot, Long> {

    List<CrowdSnapshot> findByVenueIdOrderBySnapshotTimeDesc(Long venueId);
}