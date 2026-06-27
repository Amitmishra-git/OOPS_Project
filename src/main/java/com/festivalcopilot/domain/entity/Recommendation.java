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
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private FestivalUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    private String recommendationType;

    private String reason;

    private double score;

    private LocalDateTime createdAt = LocalDateTime.now();

    protected Recommendation() {
    }

    public Recommendation(FestivalUser user, Event event, String recommendationType, String reason, double score) {
        this.user = user;
        this.event = event;
        this.recommendationType = recommendationType;
        this.reason = reason;
        this.score = score;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getRecommendationType() {
        return recommendationType;
    }

    public void setRecommendationType(String recommendationType) {
        this.recommendationType = recommendationType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}