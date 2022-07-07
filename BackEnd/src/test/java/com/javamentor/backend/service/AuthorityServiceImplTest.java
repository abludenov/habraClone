package com.javamentor.backend.service;

import com.javamentor.backend.model.Authority;
import com.javamentor.backend.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AuthorityServiceImplTest {

    private final AuthorityService authorityService;
    private final RoleService roleService;

    @Autowired
    AuthorityServiceImplTest(AuthorityService authorityService, RoleService roleService) {
        this.authorityService = authorityService;
        this.roleService = roleService;
    }

    @Test
    void getAllAuthoritiesCorrectNumberTest() {
        List<Authority> allAuthoritiesBefore = authorityService.getAllAuthorities();
        addTestAuthority();
        List<Authority> allAuthoritiesAfter = authorityService.getAllAuthorities();
        assertNotEquals(allAuthoritiesBefore.size(), allAuthoritiesAfter.size());
    }

    @Test
    void negativeGetAllAuthoritiesWrongNumberTest() {
        addTestAuthority();
        List<Authority> allAuthorities = authorityService.getAllAuthorities();
        assertNotEquals(-1, allAuthorities.size());
    }

    @Test
    void getAuthorityByCorrectNameTest() {
        addTestAuthority();
        assertNotNull(authorityService.getAuthorityByName("randomName"));
    }

    @Test
    void negativeGetAuthorityByWrongNameTest() {
        addTestAuthorityWithChanges();
        assertNull(authorityService.getAuthorityByName("randomName"));
    }

    @Test
    void negativeDeleteCanReadTopicsAuthorityTest() {
        List<Authority> allAuthoritiesBefore = authorityService.getAllAuthorities();
        addTestAuthority();
        authorityService.deleteAuthorityByName("canReadTopics");
        List<Authority> allAuthoritiesAfter = authorityService.getAllAuthorities();
        assertNotEquals(allAuthoritiesBefore.size(), allAuthoritiesAfter.size());
    }

    @Test
    void negativeDeleteNonExistingAuthorityTest() {
        List<Authority> allAuthoritiesBefore = authorityService.getAllAuthorities();

        authorityService.deleteAuthorityByName("randomName");

        List<Authority> allAuthoritiesAfter = authorityService.getAllAuthorities();
        assertEquals(allAuthoritiesBefore.size(), allAuthoritiesAfter.size());
    }

    @Test
    void deleteUnassignedAuthorityTest() {
        addTestAuthority();
        List<Authority> allAuthoritiesBefore = authorityService.getAllAuthorities();

        authorityService.deleteAuthorityByName("randomName");

        List<Authority> allAuthoritiesAfter = authorityService.getAllAuthorities();
        assertNotEquals(allAuthoritiesBefore.size(), allAuthoritiesAfter.size());
    }

    @Test
    void deleteAssignedAuthorityTest() {
        Authority testAuthority = new Authority();
        testAuthority.setName("randomName");
        authorityService.createAuthority(testAuthority);

        List<Authority> allAuthoritiesBefore = authorityService.getAllAuthorities();

        Role role = roleService.getRoleByName("ROLE_USER");
        role.getAuthorities().add(testAuthority);

        authorityService.deleteAuthorityByName("randomName");

        List<Authority> allAuthoritiesAfter = authorityService.getAllAuthorities();

        assertNotEquals(allAuthoritiesBefore.size(), allAuthoritiesAfter.size());
    }

    private void addTestAuthority() {
        Authority testAuthority = new Authority();
        testAuthority.setName("randomName");
        authorityService.createAuthority(testAuthority);
    }

    private void addTestAuthorityWithChanges() {
        Authority testAuthority = new Authority();
        testAuthority.setName("randomName");
        authorityService.createAuthority(testAuthority);

        testAuthority.setName("anotherRandomName");
        authorityService.editAuthority(testAuthority);
    }
}