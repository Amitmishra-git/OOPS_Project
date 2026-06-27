package com.festivalcopilot.api.controller;

import java.util.List;

import com.festivalcopilot.api.dto.VenueRequest;
import com.festivalcopilot.api.dto.VenueResponse;
import com.festivalcopilot.service.VenueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @PostMapping
    public ResponseEntity<VenueResponse> createVenue(@Valid @RequestBody VenueRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(venueService.createVenue(request));
    }

    @GetMapping("/{venueId}")
    public VenueResponse getVenue(@PathVariable Long venueId) {
        return venueService.getVenue(venueId);
    }

    @GetMapping
    public List<VenueResponse> getAllVenues() {
        return venueService.getAllVenues();
    }
}