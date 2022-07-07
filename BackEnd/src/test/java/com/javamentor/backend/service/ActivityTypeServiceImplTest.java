package com.javamentor.backend.service;


import com.javamentor.backend.model.ActivityType;
import com.javamentor.backend.model.NameActivityType;
import com.javamentor.backend.repository.ActivityTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


@SpringBootTest
@Transactional
class ActivityTypeServiceImplTest {
    ActivityTypeService activityTypeService;
    ActivityTypeRepository activityTypeRepository;

    @Autowired
    public ActivityTypeServiceImplTest(ActivityTypeService activityTypeService, ActivityTypeRepository activityTypeRepository) {
        this.activityTypeService = activityTypeService;
        this.activityTypeRepository = activityTypeRepository;
    }

    @Test
    void addActivityTypeTest() {
        ActivityType activityType = new ActivityType();
        activityType.setType(NameActivityType.ANSWER_THE_QUESTION);
        activityType.setChangeCoefficient(0.5);
        boolean wasAdded = activityTypeService.addActivityType(activityType);
        assertEquals(true, wasAdded);
        activityTypeRepository.delete(activityType);

    }
    @Test
    void negativeAddActivityTypeTest() {
        ActivityType activityType = new ActivityType();
        activityType.setType(null);
        activityType.setChangeCoefficient(null);
        boolean wasAdded = activityTypeService.addActivityType(activityType);
        assertFalse(wasAdded);
    }


    @Test
    void deleteActivityTypeTest() {
        int countDel = activityTypeService.deleteActivityType(NameActivityType.CREATE_COMMENT);
        assertEquals(1, countDel);
    }

    @Test
    void negativeDeleteActivityTypeTest() {
        int countDel = activityTypeService.deleteActivityType(NameActivityType.ANSWER_THE_QUESTION);
        assertEquals(0, countDel);
    }

    @Test
    void findByTypeTest() {
        assertEquals(NameActivityType.CREATE_COMMENT, activityTypeService.findByType(NameActivityType.CREATE_COMMENT).getType());
    }

    @Test
    void NegativeFindByTypeTest() {
        assertNotEquals(NameActivityType.CREATE_COMMENT, activityTypeService.findByType(NameActivityType.CREATE_TOPIC).getType());
    }

}