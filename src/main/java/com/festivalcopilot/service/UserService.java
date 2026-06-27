package com.festivalcopilot.service;

import com.festivalcopilot.api.dto.CreateUserRequest;
import com.festivalcopilot.api.dto.UpdatePreferencesRequest;
import com.festivalcopilot.api.dto.UserResponse;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    UserResponse getUser(Long userId);

    UserResponse updatePreferences(Long userId, UpdatePreferencesRequest request);
}