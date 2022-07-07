package com.javamentor.backend.service;

import com.javamentor.backend.model.Topic;
import com.javamentor.backend.model.User;
import com.javamentor.backend.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public boolean existsById(Long id) {
        return topicRepository.existsById(id);
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public List<Topic> getByTitle(String title) {
        return topicRepository.findByTitle(title);
    }

    @Override
    public List<Topic> getByAuthorsId(Long id) {
        return topicRepository.findByAuthorsId(id);
    }

    @Override
    public Topic getById(Long id) {
        if (existsById(id)) {
            return topicRepository.getById(id);
        }
        return null;
    }

    /**
     * TODO:
     *  На данный момент этот метод работает на приём постов с фронта.
     *  Если на то будет необходимость, то метод нужно будет переделать, чтобы болванка топика создавалась здесь.
     */

    @Override
    public boolean addTopic(Topic topic) {
        Topic addedTopic = topicRepository.save(topic);
        return existsById(addedTopic.getId());
    }

    @Override
    public void editTopic(Topic topic) {
        if (existsById(topic.getId())) {
            topicRepository.save(topic);
        }
    }

    @Override
    public void deleteTopicById(Long id) {
        if (existsById(id)) {
            Topic topicToDelete = topicRepository.getById(id);

            Set<User> authors = topicToDelete.getAuthors();
            authors.clear();
            topicToDelete.setAuthors(authors);

            topicToDelete.setVotes(null);
            topicToDelete.setViews(null);
            topicToDelete.setUserBookmarks(null);

            topicRepository.save(topicToDelete);
            topicRepository.deleteById(id);
        }
    }
}
