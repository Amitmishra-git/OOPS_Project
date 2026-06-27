package com.festivalcopilot.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "analytics_snapshots")
public class AnalyticsSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime snapshotTime = LocalDateTime.now();

    private Integer activeUsers;

    private Integer eventsToday;

    private Integer locations;

    private Integer alerts;

    private BigDecimal totalRevenue;

    protected AnalyticsSnapshot() {
    }

    public AnalyticsSnapshot(Integer activeUsers, Integer eventsToday, Integer locations, Integer alerts, BigDecimal totalRevenue) {
        this.activeUsers = activeUsers;
        this.eventsToday = eventsToday;
        this.locations = locations;
        this.alerts = alerts;
        this.totalRevenue = totalRevenue;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getSnapshotTime() {
        return snapshotTime;
    }

    public Integer getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(Integer activeUsers) {
        this.activeUsers = activeUsers;
    }

    public Integer getEventsToday() {
        return eventsToday;
    }

    public void setEventsToday(Integer eventsToday) {
        this.eventsToday = eventsToday;
    }

    public Integer getLocations() {
        return locations;
    }

    public void setLocations(Integer locations) {
        this.locations = locations;
    }

    public Integer getAlerts() {
        return alerts;
    }

    public void setAlerts(Integer alerts) {
        this.alerts = alerts;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}