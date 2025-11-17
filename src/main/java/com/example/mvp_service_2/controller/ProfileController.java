package com.example.mvp_service_2.controller;

import com.example.mvp_service_2.model.UserProfile;
import com.example.mvp_service_2.records.UserProfileRequest;
import com.example.mvp_service_2.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final UserProfileService service;

    public ProfileController(UserProfileService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfile> get(@PathVariable Long userId) {
        return ResponseEntity.of(service.get(userId));
    }

    @PostMapping("/{userId}")
    public UserProfile create(@PathVariable Long userId,
                              @RequestBody UserProfileRequest req) {
        return service.create(userId, req.name(), req.surname(), req.education());
    }

    @PutMapping("/{userId}")
    public UserProfile update(@PathVariable Long userId,
                              @RequestBody UserProfileRequest req) {
        return service.update(userId, req.name(), req.surname(), req.education());
    }
}
