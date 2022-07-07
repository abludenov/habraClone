package com.javamentor.backend.repository;

import com.javamentor.backend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByCompanyName(String companyName);

    Company findByUTRnumber(Long UTRnumber);

}