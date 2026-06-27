package com.festivalcopilot.service.impl;

import java.util.List;

import com.festivalcopilot.api.dto.VenueRequest;
import com.festivalcopilot.api.dto.VenueResponse;
import com.festivalcopilot.api.exception.ResourceNotFoundException;
import com.festivalcopilot.domain.entity.Venue;
import com.festivalcopilot.domain.repository.VenueRepository;
import com.festivalcopilot.service.VenueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public VenueResponse createVenue(VenueRequest request) {
        Venue venue = new Venue(request.name(), request.address(), request.latitude(), request.longitude(),
                request.capacity());
        venue.setVenueType(request.venueType());
        return toResponse(venueRepository.save(venue));
    }

    @Override
    @Transactional(readOnly = true)
    public VenueResponse getVenue(Long venueId) {
        return toResponse(findVenue(venueId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<VenueResponse> getAllVenues() {
        return venueRepository.findAll().stream().map(this::toResponse).toList();
    }

    private Venue findVenue(Long venueId) {
        return venueRepository.findById(venueId)
                .orElseThrow(() -> new ResourceNotFoundException("Venue not found: " + venueId));
    }

    private VenueResponse toResponse(Venue venue) {
        return new VenueResponse(venue.getId(), venue.getName(), venue.getAddress(), venue.getLatitude(),
                venue.getLongitude(), venue.getCapacity(), venue.getVenueType());
    }
}