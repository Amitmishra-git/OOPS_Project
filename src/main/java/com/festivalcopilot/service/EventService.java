package com.festivalcopilot.service;

import java.util.List;

import com.festivalcopilot.api.dto.EventRequest;
import com.festivalcopilot.api.dto.EventResponse;

public interface EventService {

    EventResponse createEvent(EventRequest request);

    EventResponse getEvent(Long eventId);

    List<EventResponse> getAllEvents();
}