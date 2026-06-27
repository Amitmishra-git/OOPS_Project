package com.festivalcopilot.service.impl;

import java.util.List;

import com.festivalcopilot.api.dto.EventRequest;
import com.festivalcopilot.api.dto.EventResponse;
import com.festivalcopilot.api.dto.VenueResponse;
import com.festivalcopilot.api.exception.ResourceNotFoundException;
import com.festivalcopilot.domain.entity.Event;
import com.festivalcopilot.domain.entity.Venue;
import com.festivalcopilot.domain.repository.EventRepository;
import com.festivalcopilot.domain.repository.VenueRepository;
import com.festivalcopilot.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    public EventServiceImpl(EventRepository eventRepository, VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public EventResponse createEvent(EventRequest request) {
        Event event = new Event(request.title(), request.category(), request.startTime(), request.endTime(),
                request.price());
        event.setDescription(request.description());
        if (request.score() != null) {
            event.setScore(request.score());
        }
        if (request.venueId() != null) {
            Venue venue = venueRepository.findById(request.venueId())
                    .orElseThrow(() -> new ResourceNotFoundException("Venue not found: " + request.venueId()));
            event.setVenue(venue);
        }
        return toResponse(eventRepository.save(event));
    }

    @Override
    @Transactional(readOnly = true)
    public EventResponse getEvent(Long eventId) {
        return toResponse(findEvent(eventId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream().map(this::toResponse).toList();
    }

    private Event findEvent(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found: " + eventId));
    }

    private EventResponse toResponse(Event event) {
        VenueResponse venueResponse = null;
        if (event.getVenue() != null) {
            Venue venue = event.getVenue();
            venueResponse = new VenueResponse(venue.getId(), venue.getName(), venue.getAddress(), venue.getLatitude(),
                    venue.getLongitude(), venue.getCapacity(), venue.getVenueType());
        }
        return new EventResponse(event.getId(), event.getTitle(), event.getDescription(), event.getCategory(),
                event.getStartTime(), event.getEndTime(), event.getPrice(), event.getScore(), venueResponse);
    }
}