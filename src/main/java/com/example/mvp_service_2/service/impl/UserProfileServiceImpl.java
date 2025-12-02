package com.example.mvp_service_2.service.impl;

import com.example.mvp_service_2.config.multitenant.TenantContext;
import com.example.mvp_service_2.model.UserProfile;
import com.example.mvp_service_2.records.CreateUserProfile;
import com.example.mvp_service_2.repository.UserProfileRepository;
import com.example.mvp_service_2.service.UserProfileService;
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
        return TenantContext.getTenant();
    }

    @Override
    public Optional<UserProfile> get(Long userId) {
        return tenant().equals("tenant1")
                ? repo.findByUserIdTenant1(userId)
                : repo.findByUserIdTenant2(userId);
    }

    @Override
    public UserProfile create(CreateUserProfile request) {
        if (tenant().equals("tenant1")) {
            repo.createTenant1(request.userId(), request.education());
        } else {
            repo.createTenant2(request.userId(), request.education());
        }
        return get(request.userId()).orElseThrow();
    }

    @Override
    public void delete(Long userId) {
        if (tenant().equals("tenant1")) {
            repo.deleteByUserIdTenant1(userId);
        } else {
            repo.deleteByUserIdTenant2(userId);
        }
    }
}