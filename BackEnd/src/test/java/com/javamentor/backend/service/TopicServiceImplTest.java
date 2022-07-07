package com.javamentor.backend.service;

import com.javamentor.backend.model.Topic;
import com.javamentor.backend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TopicServiceImplTest {

    private TopicService topicService;
    private UserService userService;

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    void getAllTopicsTest() {
        List<Topic> topicsBefore = topicService.getAllTopics();
        fillWithTestTopic();
        List<Topic> topicsAfter = topicService.getAllTopics();
        assertNotEquals(topicsBefore.size(), topicsAfter.size());
    }

    @Test
    void negativeGetAllTopicsTest() {
        List<Topic> topicsAfter = topicService.getAllTopics();
        assertNotEquals(-1, topicsAfter.size());
    }

    @Test
    void deleteTopicsOfAuthorByIdTest() {
        fillWithTestTopic();

        User author = userService.getUserByUsername("TestAuthor");
        List<Topic> topicsBefore = topicService.getByAuthorsId(author.getId());

        for (Topic topic : topicsBefore) {
            topicService.deleteTopicById(topic.getId());
        }

        List<Topic> topicsAfter = topicService.getByTitle("TEST-TITLE");
        assertEquals(0, topicsAfter.size());
    }

    @Test
    void deleteTopicByIdTest() {
        fillWithTestTopic();
        List<Topic> topicsBefore = topicService.getByTitle("TEST-TITLE");

        Topic topic = topicsBefore.get(0);
        topicService.deleteTopicById(topic.getId());

        User author = userService.getUserByUsername("TestAuthor");
        List<Topic> topicsAfter = topicService.getByAuthorsId(author.getId());
        assertNotEquals(topicsBefore.size(), topicsAfter.size());
    }

    @Test
    void negativeDeleteTopicByIdTest() {
        fillWithTestTopic();

        User author = userService.getUserByUsername("TestAuthor");
        List<Topic> topicsBefore = topicService.getByAuthorsId(author.getId());

        topicService.deleteTopicById(-2L);

        List<Topic> topicsAfter = topicService.getByTitle("TEST-TITLE");
        assertEquals(topicsBefore.size(), topicsAfter.size());
    }

    private void fillWithTestTopic() {
        User author = new User();
        author.setUsername("TestAuthor");
        author.setEmail("test-author@bk.co.uk");
        author.setPassword("test-author");
        author.setEnabled(true);
        userService.addUser(author);

        User voter = new User();
        voter.setUsername("TestVoter");
        voter.setEmail("test-voter@bk.co.uk");
        voter.setPassword("test-voter");
        voter.setEnabled(true);
        userService.addUser(voter);

        User viewer = new User();
        viewer.setUsername("TestViewer");
        viewer.setEmail("test-viewer@bk.co.uk");
        viewer.setPassword("test-viewer");
        viewer.setEnabled(true);
        userService.addUser(viewer);

        User userWithBookmarks = new User();
        userWithBookmarks.setUsername("TestUserWithBookmarks");
        userWithBookmarks.setEmail("test-userWithBookmarks@bk.co.uk");
        userWithBookmarks.setPassword("test-userWithBookmarks");
        userWithBookmarks.setEnabled(true);
        userService.addUser(userWithBookmarks);

        Set<User> authors = new HashSet<>();
        authors.add(author);
        Set<User> voters = new HashSet<>();
        voters.add(voter);
        Set<User> viewers = new HashSet<>();
        viewers.add(viewer);
        Set<User> usersWithBookmarks = new HashSet<>();
        usersWithBookmarks.add(userWithBookmarks);

        Topic topic = new Topic();
        topic.setTitle("TEST-TITLE");
        topic.setPublished(LocalDateTime.now());
        topic.setContent("Test content");
        topic.setAuthors(authors);
        topic.setVotes(voters);
        topic.setViews(viewers);
        topic.setUserBookmarks(usersWithBookmarks);
        topicService.addTopic(topic);

        topic.setContent("Another test content");
        topicService.editTopic(topic);
    }
}