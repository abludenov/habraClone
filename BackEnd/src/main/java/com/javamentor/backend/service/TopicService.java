package com.javamentor.backend.service;

import com.javamentor.backend.model.Topic;

import java.util.List;

public interface TopicService {

    boolean existsById(Long id);

    List<Topic> getAllTopics();

    List<Topic> getByTitle(String title);

    List<Topic> getByAuthorsId(Long id);

    Topic getById(Long id);

    boolean addTopic(Topic topic);

    void editTopic(Topic topic);

    void deleteTopicById(Long id);
}
