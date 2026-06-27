package com.festivalcopilot.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivalcopilot.domain.entity.FestivalUser;

public interface FestivalUserRepository extends JpaRepository<FestivalUser, Long> {

    Optional<FestivalUser> findByEmail(String email);
}