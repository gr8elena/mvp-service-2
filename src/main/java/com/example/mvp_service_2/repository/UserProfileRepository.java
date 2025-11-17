package com.example.mvp_service_2.repository;

import com.example.mvp_service_2.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    // === TENANT 1 ===
    @Query(value = "SELECT * FROM user_profile_tenant1 WHERE user_id = :userId", nativeQuery = true)
    Optional<UserProfile> findByUserIdTenant1(@Param("userId") Long userId);

    @Modifying
    @Query(value = """
        INSERT INTO user_profile_tenant1
          (user_id, name, surname, education, created_at, updated_at)
        VALUES (:userId, :name, :surname, :education, NOW(), NOW())
        """, nativeQuery = true)
    void createTenant1(@Param("userId") Long userId,
                       @Param("name") String name,
                       @Param("surname") String surname,
                       @Param("education") String education);

    @Modifying
    @Query(value = """
        UPDATE user_profile_tenant1
           SET name = :name, surname = :surname, education = :education, updated_at = NOW()
         WHERE user_id = :userId
        """, nativeQuery = true)
    int updateTenant1(@Param("userId") Long userId,
                      @Param("name") String name,
                      @Param("surname") String surname,
                      @Param("education") String education);

    // === TENANT 2 ===
    @Query(value = "SELECT * FROM user_profile_tenant2 WHERE user_id = :userId", nativeQuery = true)
    Optional<UserProfile> findByUserIdTenant2(@Param("userId") Long userId);

    @Modifying
    @Query(value = """
        INSERT INTO user_profile_tenant2
          (user_id, name, surname, education, created_at, updated_at)
        VALUES (:userId, :name, :surname, :education, NOW(), NOW())
        """, nativeQuery = true)
    void createTenant2(@Param("userId") Long userId,
                       @Param("name") String name,
                       @Param("surname") String surname,
                       @Param("education") String education);

    @Modifying
    @Query(value = """
        UPDATE user_profile_tenant2
           SET name = :name, surname = :surname, education = :education, updated_at = NOW()
         WHERE user_id = :userId
        """, nativeQuery = true)
    int updateTenant2(@Param("userId") Long userId,
                      @Param("name") String name,
                      @Param("surname") String surname,
                      @Param("education") String education);
}