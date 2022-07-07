package com.javamentor.backend.service;

import com.javamentor.backend.model.ActivityType;
import com.javamentor.backend.model.NameActivityType;

public interface ActivityTypeService {

    ActivityType findById(Long id);

    boolean addActivityType(ActivityType activityType);

    int deleteActivityType(NameActivityType type);

    ActivityType findByType(NameActivityType type);
}
