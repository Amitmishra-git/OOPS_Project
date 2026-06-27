package com.festivalcopilot.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivalcopilot.domain.entity.UserEventInteraction;

public interface UserEventInteractionRepository extends JpaRepository<UserEventInteraction, Long> {

    List<UserEventInteraction> findByUserId(Long userId);
}