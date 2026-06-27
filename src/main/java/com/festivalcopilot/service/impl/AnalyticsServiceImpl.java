package com.festivalcopilot.service.impl;

import java.math.BigDecimal;

import com.festivalcopilot.api.dto.AnalyticsOverviewResponse;
import com.festivalcopilot.domain.repository.AnalyticsSnapshotRepository;
import com.festivalcopilot.domain.repository.CrowdSnapshotRepository;
import com.festivalcopilot.domain.repository.EventRepository;
import com.festivalcopilot.domain.repository.FestivalUserRepository;
import com.festivalcopilot.domain.repository.TransactionRecordRepository;
import com.festivalcopilot.domain.repository.VenueRepository;
import com.festivalcopilot.service.AnalyticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AnalyticsServiceImpl implements AnalyticsService {

    private final FestivalUserRepository userRepository;
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final CrowdSnapshotRepository crowdSnapshotRepository;
    private final TransactionRecordRepository transactionRecordRepository;
    @SuppressWarnings("unused")
    private final AnalyticsSnapshotRepository analyticsSnapshotRepository;

    public AnalyticsServiceImpl(FestivalUserRepository userRepository, EventRepository eventRepository,
            VenueRepository venueRepository, CrowdSnapshotRepository crowdSnapshotRepository,
            TransactionRecordRepository transactionRecordRepository,
            AnalyticsSnapshotRepository analyticsSnapshotRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
        this.crowdSnapshotRepository = crowdSnapshotRepository;
        this.transactionRecordRepository = transactionRecordRepository;
        this.analyticsSnapshotRepository = analyticsSnapshotRepository;
    }

    @Override
    public AnalyticsOverviewResponse getOverview() {
        long activeUsers = userRepository.count();
        long eventsToday = eventRepository.count();
        long locations = venueRepository.count();
        long alerts = crowdSnapshotRepository.findAll().stream()
                .filter(snapshot -> snapshot.getCongestionRisk() != null && snapshot.getCongestionRisk() >= 0.7)
                .count();
        BigDecimal totalRevenue = transactionRecordRepository.findAll().stream()
                .map(record -> record.getAmount() == null ? BigDecimal.ZERO : record.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new AnalyticsOverviewResponse(activeUsers, eventsToday, locations, alerts, totalRevenue);
    }
}