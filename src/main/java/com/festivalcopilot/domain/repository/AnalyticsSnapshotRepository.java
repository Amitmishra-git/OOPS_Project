package com.festivalcopilot.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivalcopilot.domain.entity.AnalyticsSnapshot;

public interface AnalyticsSnapshotRepository extends JpaRepository<AnalyticsSnapshot, Long> {

    List<AnalyticsSnapshot> findTop10ByOrderBySnapshotTimeDesc();
}