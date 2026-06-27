package com.festivalcopilot.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.festivalcopilot.api.dto.RecommendationResponse;
import com.festivalcopilot.api.exception.ResourceNotFoundException;
import com.festivalcopilot.domain.entity.Event;
import com.festivalcopilot.domain.entity.FestivalUser;
import com.festivalcopilot.domain.repository.EventRepository;
import com.festivalcopilot.domain.repository.FestivalUserRepository;
import com.festivalcopilot.service.RecommendationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RecommendationServiceImpl implements RecommendationService {

    private final FestivalUserRepository userRepository;
    private final EventRepository eventRepository;

    public RecommendationServiceImpl(FestivalUserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<RecommendationResponse> recommendEvents(Long userId) {
        FestivalUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
        List<String> preferredGenres = splitValues(user.getPreferredGenres());
        BigDecimal budget = parseBudget(user.getBudgetPreference());

        return eventRepository.findAll().stream()
                .map(event -> toRecommendation(event, preferredGenres, budget))
                .sorted(Comparator.comparingDouble(RecommendationResponse::score).reversed())
                .limit(10)
                .toList();
    }

    private RecommendationResponse toRecommendation(Event event, List<String> preferredGenres, BigDecimal budget) {
        double score = event.getScore();
        List<String> reasons = new ArrayList<>();

        if (preferredGenres.stream().anyMatch(genre -> containsIgnoreCase(event.getCategory(), genre))) {
            score += 35;
            reasons.add("matches your interests");
        }

        if (budget != null && event.getPrice() != null && event.getPrice().compareTo(budget) <= 0) {
            score += 20;
            reasons.add("fits your budget");
        }

        if (event.getVenue() != null) {
            score += 5;
            reasons.add("has a known venue");
        }

        String reason = reasons.isEmpty() ? "popular event" : String.join(", ", reasons);
        return new RecommendationResponse(event.getId(), event.getTitle(), event.getCategory(), score, reason);
    }

    private List<String> splitValues(String raw) {
        if (raw == null || raw.isBlank()) {
            return List.of();
        }
        return List.of(raw.split("\\s*,\\s*"));
    }

    private BigDecimal parseBudget(String raw) {
        if (raw == null || raw.isBlank()) {
            return null;
        }
        try {
            return new BigDecimal(raw.trim());
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    private boolean containsIgnoreCase(String source, String fragment) {
        if (source == null || fragment == null) {
            return false;
        }
        return source.toLowerCase().contains(fragment.toLowerCase());
    }
}