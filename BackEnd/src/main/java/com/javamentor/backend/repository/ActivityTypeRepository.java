package com.javamentor.backend.repository;

import com.javamentor.backend.model.ActivityType;
import com.javamentor.backend.model.NameActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {

    ActivityType findByType(NameActivityType type);

    int deleteByType(NameActivityType type);

    boolean existsByType(NameActivityType type);

}
