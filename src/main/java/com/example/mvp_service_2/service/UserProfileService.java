package com.example.mvp_service_2.service;

import com.example.mvp_service_2.model.UserProfile;
import com.example.mvp_service_2.records.CreateUserProfile;

import java.util.Optional;

public interface UserProfileService {
    Optional<UserProfile> get(Long userId);
    UserProfile create(CreateUserProfile request);
    void delete(Long userId);
}
