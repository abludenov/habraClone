package com.javamentor.backend.service;

import com.javamentor.backend.model.Company;
import com.javamentor.backend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void deleteCompanyById(Long companyId) {

        Optional<Company> company = companyRepository.findById(companyId);

        company.ifPresent(companyFinded -> companyFinded.setEnabled(false));
        company.ifPresent(companyRepository::save);

    }

    @Override
    public Company findByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }

    @Override
    public Company findByUTRnumber(Long UTRnumber) {
        return companyRepository.findByUTRnumber(UTRnumber);
    }
}
