package com.festivalcopilot.service;

import java.util.List;

import com.festivalcopilot.api.dto.VenueRequest;
import com.festivalcopilot.api.dto.VenueResponse;

public interface VenueService {

    VenueResponse createVenue(VenueRequest request);

    VenueResponse getVenue(Long venueId);

    List<VenueResponse> getAllVenues();
}