package com.javamentor.backend.repository;

import com.javamentor.backend.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    boolean existsById(Long id);

    List<Topic> findByTitle(String title);

    List<Topic> findByAuthorsId(Long id);
}