package com.javamentor.backend.service;

import com.javamentor.backend.model.Authority;
import com.javamentor.backend.model.Company;
import com.javamentor.backend.model.Role;
import com.javamentor.backend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CompanyServiceImplTest {

    @Autowired
    private CompanyService companyService;

    @Test
    public void getAllCompaniesTestSuccessful() {

        List<Company> companyList = companyService.getAllCompanies();
        assertEquals(3, companyList.size());
    }

    @Test
    public void getAllCompaniesTestWrong() {

        List<Company> companyList = companyService.getAllCompanies();
        assertNotEquals(4, companyList.size());
    }

    @Test
    public void saveCompanyTestSuccessful() {

        Company testCompany = new Company();

        companyFillWithTestData(testCompany);
        companyService.saveCompany(testCompany);

        List<Company> companyList = companyService.getAllCompanies();
        assertEquals(4, companyList.size());
    }

    @Test
    public void saveCompanyTestWrong() {

        Company testCompany = new Company();

        companyFillWithTestData(testCompany);
        companyService.saveCompany(testCompany);

        List<Company> companyList = companyService.getAllCompanies();
        assertNotEquals(5, companyList.size());
    }

    @Test
    public void deleteCompanyTestSuccessful() {

        Company testCompany = new Company();
        companyFillWithTestData(testCompany);
        companyService.saveCompany(testCompany);
        assertTrue(testCompany.isEnabled());

        companyService.deleteCompanyById(4L);
        assertFalse(testCompany.isEnabled());
    }

    @Test
    public void deleteCompanyTestWrong() {

        Company testCompany = new Company();
        companyFillWithTestData(testCompany);
        companyService.saveCompany(testCompany);
        assertTrue(testCompany.isEnabled());

        companyService.deleteCompanyById(0L);
        assertTrue(testCompany.isEnabled());

    }

    @Test
    public void findByCompanyNameTestSuccessful() {

        Company testCompany = new Company();
        companyFillWithTestData(testCompany);
        companyService.saveCompany(testCompany);

        Company findedByCompanyName = companyService.findByCompanyName("TEST");
        assertEquals(testCompany.getCompanyName(), findedByCompanyName.getCompanyName());
    }

    @Test
    public void findByCompanyNameTestWrong() {

        assertNull(companyService.findByCompanyName("wrongNameData"));
    }

    @Test
    public void findByUTRNumberTestSuccessful() {

        Company testCompany = new Company();
        companyFillWithTestData(testCompany);
        companyService.saveCompany(testCompany);

        Company findedByUTRCompany = companyService.findByUTRnumber(12131415L);
        assertEquals(testCompany.getUTRnumber(), findedByUTRCompany.getUTRnumber());
    }

    @Test
    public void findByUTRNumberTestWrong() {

        assertNull(companyService.findByUTRnumber(0L));
    }

    //метод наполнения данными админов для теста
    private Set<User> companyAdminsTestData() {
        Set<User> companyAdminsForTest = new HashSet<>();
        Set<Role> companyAdminsRolesForTest = new HashSet<>();
        Set<Authority> companyAdminsAuthorityForTest = new HashSet<>();

        User admin = new User();
        Role adminRoleForCompanyTest = new Role();
        Authority adminAuthorityForCompanyTest = new Authority();

        adminAuthorityForCompanyTest.setName("adminAuthorityForCompanyTest");

        adminRoleForCompanyTest.setName("adminRoleForCompanyTest");
        adminRoleForCompanyTest.setAuthorities(companyAdminsAuthorityForTest);


        admin.setUsername("adminForTestCompany");
        admin.setEmail("adminForTestCompany@gmail.com");
        admin.setPassword("sudoCompanyTest");
        admin.setEnabled(true);
        admin.setRoles(companyAdminsRolesForTest);
        admin.setBadges(null);
        admin.setFollowers(null);
        admin.setFollowings(null);
        admin.setInvitedUsers(null);

        companyAdminsAuthorityForTest.add(adminAuthorityForCompanyTest);
        companyAdminsRolesForTest.add(adminRoleForCompanyTest);
        companyAdminsForTest.add(admin);

        return companyAdminsForTest;
    }

    //метод для наполнения компании тестовыми данными
    private Company companyFillWithTestData(Company company) {

        company.setCompanyName("TEST");
        company.setUTRnumber(12131415L);
        company.setCompanyRating(null);
        company.setCompanyAdmins(companyAdminsTestData());
        company.setCompanySite("companySite@gmail.com");
        company.setCompanyScale(3L);
        company.setAboutCompany("TestAboutCompany");
        company.setCompanyRating(null);
        company.setEnabled(true);

        return company;
    }
}