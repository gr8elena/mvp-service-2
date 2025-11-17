package com.example.mvp_service_2.service.impl;

import com.example.mvp_service_2.config.multitenant.TenantContext;
import com.example.mvp_service_2.model.UserProfile;
import com.example.mvp_service_2.repository.UserProfileRepository;
import com.example.mvp_service_2.service.UserProfileService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repo;

    public UserProfileServiceImpl(UserProfileRepository repo) {
        this.repo = repo;
    }

    private String tenant() {
        return TenantContext.getTenant(); // "tenant1" or "tenant2"
    }

    @Override
    public Optional<UserProfile> get(Long userId) {
        return tenant().equals("tenant1")
                ? repo.findByUserIdTenant1(userId)
                : repo.findByUserIdTenant2(userId);
    }

    @Override
    public UserProfile create(Long userId, String name, String surname, String education) {
        if (tenant().equals("tenant1")) {
            repo.createTenant1(userId, name, surname, education);
        } else {
            repo.createTenant2(userId, name, surname, education);
        }
        return get(userId).orElseThrow();
    }

    @Override
    public UserProfile update(Long userId, String name, String surname, String education) {
        int updated = tenant().equals("tenant1")
                ? repo.updateTenant1(userId, name, surname, education)
                : repo.updateTenant2(userId, name, surname, education);

        if (updated == 0) throw new RuntimeException("Profile not found");
        return get(userId).orElseThrow();
    }
}