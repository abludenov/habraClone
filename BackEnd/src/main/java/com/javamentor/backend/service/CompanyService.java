package com.javamentor.backend.service;

import com.javamentor.backend.model.Company;

import java.util.List;

public interface CompanyService {

    public List<Company> getAllCompanies();

    public void saveCompany(Company company);

    public void deleteCompanyById(Long companyId);

    public Company findByCompanyName(String companyName);

    public Company findByUTRnumber(Long UTRnumber);

}
