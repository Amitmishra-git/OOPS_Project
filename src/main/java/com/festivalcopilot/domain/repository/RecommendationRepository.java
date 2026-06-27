package com.festivalcopilot.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivalcopilot.domain.entity.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    List<Recommendation> findByUserIdOrderByScoreDesc(Long userId);
}