package com.javamentor.backend.service;

import com.javamentor.backend.model.Authority;

import java.util.List;

public interface AuthorityService {

    boolean existsAuthorityByName(String name);

    List<Authority> getAllAuthorities();

    Authority getAuthorityByName(String name);

    void createAuthority(Authority authority);

    void editAuthority(Authority authority);

    void deleteAuthorityByName(String name);
}
