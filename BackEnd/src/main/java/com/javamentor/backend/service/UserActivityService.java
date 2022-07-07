package com.javamentor.backend.service;

import com.javamentor.backend.model.NameActivityType;
import com.javamentor.backend.model.User;
import com.javamentor.backend.model.UserActivity;

import java.util.List;

public interface UserActivityService {

    void createUserActivity(User user, NameActivityType typeActivity, Boolean changeUp);

    boolean existsUserActivitiesByUserId(long userId);

    List<UserActivity> findByActivityType(NameActivityType typeActivity);

    List<UserActivity> findByUserId(Long userId);
}