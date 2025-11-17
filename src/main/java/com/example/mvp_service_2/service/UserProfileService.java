package com.example.mvp_service_2.service;

import com.example.mvp_service_2.model.UserProfile;

import java.util.Optional;

public interface UserProfileService {
    Optional<UserProfile> get(Long userId);

    UserProfile create(Long userId, String name, String surname, String education);

    UserProfile update(Long userId, String name, String surname, String education);
}
