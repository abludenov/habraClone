package com.javamentor.backend.service;

import com.javamentor.backend.model.ActivityType;
import com.javamentor.backend.model.NameActivityType;
import com.javamentor.backend.model.User;
import com.javamentor.backend.model.UserActivity;
import com.javamentor.backend.repository.ActivityTypeRepository;
import com.javamentor.backend.repository.UserActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserActivityServiceImpl implements UserActivityService {

    private final UserActivityRepository userActivityRepository;
    private final ActivityTypeRepository activityTypeRepository;

    public UserActivityServiceImpl(UserActivityRepository userActivityRepository, ActivityTypeRepository activityTypeRepository) {
        this.userActivityRepository = userActivityRepository;
        this.activityTypeRepository = activityTypeRepository;
    }

    @Override
    public void createUserActivity(User user, NameActivityType typeActivity, Boolean changeUp) {
        if (user.getId() != null) {
            UserActivity userActivity = new UserActivity();
            ActivityType activityType = activityTypeRepository.findByType(typeActivity);
            userActivity.setActivityType(activityType);
            userActivity.setUser(user);
            userActivity.setLastActivity(LocalDateTime.now());
            userActivity.setChangeUp(changeUp);

            userActivityRepository.save(userActivity);
        }
    }

    @Override
    public boolean existsUserActivitiesByUserId(long userId) {
        return userActivityRepository.existsUserActivitiesByUserId(userId);
    }

    @Override
    public List<UserActivity> findByActivityType(NameActivityType typeActivity) {
        ActivityType activityType = activityTypeRepository.findByType(typeActivity);
        return userActivityRepository.findByActivityType(activityType);
    }

    @Override
    public List<UserActivity> findByUserId(Long userId) {
        return userActivityRepository.findByUserId(userId);
    }
}
