package com.festivalcopilot.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivalcopilot.domain.entity.TransactionRecord;

public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Long> {

    List<TransactionRecord> findByUserId(Long userId);
}