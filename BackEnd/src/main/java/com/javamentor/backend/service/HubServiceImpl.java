package com.javamentor.backend.service;

import com.javamentor.backend.model.Hub;
import com.javamentor.backend.repository.CompanyRepository;
import com.javamentor.backend.repository.HubRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HubServiceImpl implements HubService {

    private final CompanyRepository companyRepository;
    private final HubRepository hubRepository;

    public HubServiceImpl(HubRepository hubRepository, CompanyRepository companyRepository) {
        this.hubRepository = hubRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public List<Hub> getAllHubs() {
        return hubRepository.findAll();
    }

    @Override
    public void saveHub(Hub hub) {
        hubRepository.save(hub);
    }

    @Override
    public void deleteHubById(Long id) {
        hubRepository.getById(id).setTopics(null);
        companyRepository.getById(id).setCompanyHubs(null);
        hubRepository.deleteById(id);
    }

    @Override
    public List<Hub> findByTitle(String title) {
        return hubRepository.findByTitle(title);
    }

    @Override
    public List<Hub> getByTopicId(Long id) {
        return hubRepository.getByTopicsId(id);
    }

}
