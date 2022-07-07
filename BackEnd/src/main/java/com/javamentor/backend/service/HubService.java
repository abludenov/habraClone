package com.javamentor.backend.service;

import com.javamentor.backend.model.Hub;

import java.util.List;

public interface HubService {

   List<Hub> getAllHubs();
   void saveHub(Hub hub);
   void deleteHubById(Long id);
   List<Hub> findByTitle(String title);
   List<Hub> getByTopicId(Long id);

}
