package com.example.mvp_service_2.controller;

import com.example.mvp_service_2.model.UserProfile;
import com.example.mvp_service_2.model.dto.UserProfileResponse;
import com.example.mvp_service_2.records.CreateUserProfile;
import com.example.mvp_service_2.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/api/profiles/{userId}")
    public UserProfileResponse getProfile(@PathVariable Long userId) {
        UserProfile profile = userProfileService.get(userId).orElseThrow(() -> new RuntimeException("User profile not found"));

        return new UserProfileResponse(profile.getEducation());
    }

    @PostMapping("/api/profiles")
    public UserProfile createProfile(@RequestBody CreateUserProfile request) {
        return userProfileService.create(request);
    }

    @DeleteMapping("/api/profiles/{userId}")
    public void deleteProfile(@PathVariable Long userId) {
        userProfileService.delete(userId);
    }
}

