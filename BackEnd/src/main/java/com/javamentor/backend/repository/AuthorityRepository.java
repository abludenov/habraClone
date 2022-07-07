package com.javamentor.backend.repository;

import com.javamentor.backend.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(String name);

    boolean existsByName(String name);

    void deleteByName(String name);
}