package com.javamentor.backend.service;


import com.javamentor.backend.model.Hub;
import com.javamentor.backend.model.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
class HubServiceImplTest {

    @Autowired
    private HubService hubService;


    @Test
    void saveHubTest() {
        Hub h1 = new Hub();
        h1.setTitle("TEST");
        hubService.saveHub(h1);
        List<Hub> hubList = hubService.getAllHubs();
        System.out.println(hubList.size());
        assertEquals(4, hubList.size());
    }

    @Test
    void deleteHubByIdTest() {
        hubService.deleteHubById(1L);
        List<Hub> hubList = hubService.getAllHubs();
        assertEquals(2, hubList.size());
    }

    @Test
    void findByTitleTest() {
        Hub h1 = new Hub();
        h1.setTitle("TEST");
        hubService.saveHub(h1);
        List<Hub> hubList = hubService.findByTitle("TEST");
        assertEquals(1, hubList.size());
    }

    @Test
    void getByTopicIdTest() {
        List<Hub> topicList = hubService.getByTopicId(2L);
        assertEquals(1,topicList.size());
        Set<Topic> topicSet = topicList.get(0).getTopics();
        assertEquals(1, topicSet.size());
    }

}