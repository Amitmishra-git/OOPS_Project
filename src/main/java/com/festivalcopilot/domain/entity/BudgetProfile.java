package com.festivalcopilot.domain.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "budget_profiles")
public class BudgetProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private FestivalUser user;

    private BigDecimal totalBudget;

    private BigDecimal spentBudget;

    private String currency = "INR";

    protected BudgetProfile() {
    }

    public BudgetProfile(FestivalUser user, BigDecimal totalBudget) {
        this.user = user;
        this.totalBudget = totalBudget;
        this.spentBudget = BigDecimal.ZERO;
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

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public BigDecimal getSpentBudget() {
        return spentBudget;
    }

    public void setSpentBudget(BigDecimal spentBudget) {
        this.spentBudget = spentBudget;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}