package com.festivalcopilot.service.impl;

import com.festivalcopilot.api.dto.CreateUserRequest;
import com.festivalcopilot.api.dto.UpdatePreferencesRequest;
import com.festivalcopilot.api.dto.UserResponse;
import com.festivalcopilot.api.exception.ResourceNotFoundException;
import com.festivalcopilot.domain.entity.FestivalUser;
import com.festivalcopilot.domain.repository.FestivalUserRepository;
import com.festivalcopilot.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final FestivalUserRepository userRepository;

    public UserServiceImpl(FestivalUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        FestivalUser user = new FestivalUser(request.fullName(), request.email(), request.passwordHash());
        user.setPreferredGenres(request.preferredGenres());
        user.setBudgetPreference(request.budgetPreference());
        return toResponse(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUser(Long userId) {
        return toResponse(findUser(userId));
    }

    @Override
    public UserResponse updatePreferences(Long userId, UpdatePreferencesRequest request) {
        FestivalUser user = findUser(userId);
        user.setPreferredGenres(request.preferredGenres());
        user.setBudgetPreference(request.budgetPreference());
        return toResponse(userRepository.save(user));
    }

    private FestivalUser findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
    }

    private UserResponse toResponse(FestivalUser user) {
        return new UserResponse(user.getId(), user.getFullName(), user.getEmail(), user.getPreferredGenres(),
                user.getBudgetPreference());
    }
}