package com.festivalcopilot.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.festivalcopilot.api.dto.ScheduleItemResponse;
import com.festivalcopilot.api.dto.ScheduleResponse;
import com.festivalcopilot.api.exception.ResourceNotFoundException;
import com.festivalcopilot.domain.entity.Event;
import com.festivalcopilot.domain.entity.FestivalUser;
import com.festivalcopilot.domain.entity.Schedule;
import com.festivalcopilot.domain.entity.ScheduleItem;
import com.festivalcopilot.domain.repository.EventRepository;
import com.festivalcopilot.domain.repository.FestivalUserRepository;
import com.festivalcopilot.domain.repository.ScheduleItemRepository;
import com.festivalcopilot.domain.repository.ScheduleRepository;
import com.festivalcopilot.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final FestivalUserRepository userRepository;
    private final EventRepository eventRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleItemRepository scheduleItemRepository;

    public ScheduleServiceImpl(FestivalUserRepository userRepository, EventRepository eventRepository,
            ScheduleRepository scheduleRepository, ScheduleItemRepository scheduleItemRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.scheduleRepository = scheduleRepository;
        this.scheduleItemRepository = scheduleItemRepository;
    }

    @Override
    @Transactional
    public ScheduleResponse generateSchedule(Long userId) {
        FestivalUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));

        Schedule schedule = scheduleRepository.findByUserId(userId)
                .orElseGet(() -> scheduleRepository.save(new Schedule(user, "Festival Schedule")));

        scheduleItemRepository.deleteAll(scheduleItemRepository.findByScheduleIdOrderByDisplayOrderAsc(schedule.getId()));

        List<Event> selectedEvents = eventRepository.findAll().stream()
                .filter(event -> event.getStartTime() != null && event.getEndTime() != null)
                .sorted(Comparator.comparing(Event::getStartTime))
                .toList();

        List<ScheduleItemResponse> plannedItems = new ArrayList<>();
        int displayOrder = 1;
        java.time.LocalDateTime lastEndTime = null;

        for (Event event : selectedEvents) {
            if (lastEndTime != null && !event.getStartTime().isAfter(lastEndTime)) {
                continue;
            }

            ScheduleItem item = new ScheduleItem(schedule, event, event.getStartTime(), event.getEndTime());
            item.setDisplayOrder(displayOrder++);
            scheduleItemRepository.save(item);

            plannedItems.add(new ScheduleItemResponse(event.getId(), event.getTitle(), event.getStartTime(),
                    event.getEndTime(), item.getDisplayOrder()));
            lastEndTime = event.getEndTime();
        }

        return new ScheduleResponse(schedule.getId(), userId, schedule.getTitle(), plannedItems);
    }

    @Override
    @Transactional(readOnly = true)
    public ScheduleResponse getSchedule(Long userId) {
        Schedule schedule = scheduleRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found for user: " + userId));
        List<ScheduleItemResponse> items = scheduleItemRepository.findByScheduleIdOrderByDisplayOrderAsc(schedule.getId())
                .stream()
                .map(item -> new ScheduleItemResponse(item.getEvent().getId(), item.getEvent().getTitle(),
                        item.getPlannedStartTime(), item.getPlannedEndTime(), item.getDisplayOrder()))
                .toList();
        return new ScheduleResponse(schedule.getId(), userId, schedule.getTitle(), items);
    }
}