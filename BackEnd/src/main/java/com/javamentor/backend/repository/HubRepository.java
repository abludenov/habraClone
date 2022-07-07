package com.javamentor.backend.repository;

import com.javamentor.backend.model.Hub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HubRepository extends JpaRepository<Hub, Long> {
     List<Hub> findByTitle(String title);
     List<Hub> getByTopicsId(Long id);
}
