package com.javamentor.backend.repository;

import com.javamentor.backend.model.ActivityType;
import com.javamentor.backend.model.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    List<UserActivity> findByActivityType(ActivityType activityType);

    boolean existsUserActivitiesByUserId(long userId);

    List<UserActivity> findByUserId(Long userId);
}