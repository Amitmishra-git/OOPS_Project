package com.festivalcopilot.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class TransactionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private FestivalUser user;

    private BigDecimal amount;

    private String category;

    private String description;

    private LocalDateTime transactionTime = LocalDateTime.now();

    protected TransactionRecord() {
    }

    public TransactionRecord(FestivalUser user, BigDecimal amount, String category, String description) {
        this.user = user;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public FestivalUser getUser() {
        return user;
    }

    public void setUser(FestivalUser user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }
}