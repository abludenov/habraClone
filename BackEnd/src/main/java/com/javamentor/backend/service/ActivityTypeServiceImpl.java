package com.javamentor.backend.service;

import com.javamentor.backend.model.ActivityType;
import com.javamentor.backend.model.NameActivityType;
import com.javamentor.backend.repository.ActivityTypeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityTypeServiceImpl implements ActivityTypeService {

    private final ActivityTypeRepository activityTypeRepository;

    public ActivityTypeServiceImpl(ActivityTypeRepository activityTypeRepository) {
        this.activityTypeRepository = activityTypeRepository;
    }

    public List<ActivityType> findAll() {
        return activityTypeRepository.findAll();
    }

    public ActivityType findById(Long id) {
        return activityTypeRepository.findById(id).get();
    }


    public boolean addActivityType(ActivityType activityType) {
        try {
            if (activityTypeRepository.existsByType(activityType.getType())) {
                return false;
            }
            activityTypeRepository.save(activityType);
            return true;
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return false;
        }
    }


    public int deleteActivityType(NameActivityType type) {
        return activityTypeRepository.deleteByType(type);
    }

    public ActivityType findByType(NameActivityType type) {
        return activityTypeRepository.findByType(type);
    }
}
