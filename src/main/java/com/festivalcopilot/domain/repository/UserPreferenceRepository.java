package com.festivalcopilot.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivalcopilot.domain.entity.UserPreference;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {

    List<UserPreference> findByUserId(Long userId);
}