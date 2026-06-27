package com.festivalcopilot.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivalcopilot.domain.entity.BudgetProfile;

public interface BudgetProfileRepository extends JpaRepository<BudgetProfile, Long> {

    Optional<BudgetProfile> findByUserId(Long userId);
}