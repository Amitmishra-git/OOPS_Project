package com.festivalcopilot.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_preferences")
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private FestivalUser user;

    private String preferenceKey;

    private String preferenceValue;

    protected UserPreference() {
    }

    public UserPreference(FestivalUser user, String preferenceKey, String preferenceValue) {
        this.user = user;
        this.preferenceKey = preferenceKey;
        this.preferenceValue = preferenceValue;
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

    public String getPreferenceKey() {
        return preferenceKey;
    }

    public void setPreferenceKey(String preferenceKey) {
        this.preferenceKey = preferenceKey;
    }

    public String getPreferenceValue() {
        return preferenceValue;
    }

    public void setPreferenceValue(String preferenceValue) {
        this.preferenceValue = preferenceValue;
    }
}