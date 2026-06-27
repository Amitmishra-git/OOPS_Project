package com.festivalcopilot.domain.entity;

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
@Table(name = "crowd_snapshots")
public class CrowdSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    private Venue venue;

    private LocalDateTime snapshotTime;

    private Integer actualCount;

    private Integer predictedCount;

    private String crowdLevel;

    private Double congestionRisk;

    protected CrowdSnapshot() {
    }

    public CrowdSnapshot(Venue venue, LocalDateTime snapshotTime, Integer actualCount, Integer predictedCount) {
        this.venue = venue;
        this.snapshotTime = snapshotTime;
        this.actualCount = actualCount;
        this.predictedCount = predictedCount;
    }

    public Long getId() {
        return id;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public LocalDateTime getSnapshotTime() {
        return snapshotTime;
    }

    public void setSnapshotTime(LocalDateTime snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    public Integer getActualCount() {
        return actualCount;
    }

    public void setActualCount(Integer actualCount) {
        this.actualCount = actualCount;
    }

    public Integer getPredictedCount() {
        return predictedCount;
    }

    public void setPredictedCount(Integer predictedCount) {
        this.predictedCount = predictedCount;
    }

    public String getCrowdLevel() {
        return crowdLevel;
    }

    public void setCrowdLevel(String crowdLevel) {
        this.crowdLevel = crowdLevel;
    }

    public Double getCongestionRisk() {
        return congestionRisk;
    }

    public void setCongestionRisk(Double congestionRisk) {
        this.congestionRisk = congestionRisk;
    }
}