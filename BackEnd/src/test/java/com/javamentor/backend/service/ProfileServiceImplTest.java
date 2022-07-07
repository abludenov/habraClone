package com.javamentor.backend.service;

import com.javamentor.backend.model.GenderEnum;
import com.javamentor.backend.model.Profile;
import com.javamentor.backend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProfileServiceImplTest {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserService userService;

    @Test
    void saveDefaultProfileTest(){
        User user = new User();
        user.setUsername("Dima");
        user.setEmail("dima@email.com");
        user.setPassword("test");

        userService.addUser(user);
        assertTrue(profileService.saveDefaultProfile(user));

    }

    @Test
    void negativeSaveDefaultProfileTest(){
        User userFromBase = userService.getUserById(1L);
        profileService.saveDefaultProfile(userFromBase);
        assertFalse (profileService.saveDefaultProfile(userFromBase));
        assertNotEquals(GenderEnum.OTHER, profileService.getProfileByUserId(1L).getGender());
    }



    @Test
    void updateProfileTest(){
        Profile profile = profileService.getProfileByUserId(2L);
        profile.setGender(GenderEnum.OTHER);
        profile.setActualName("Name Testov");
        profile.setBirthDay(LocalDate.of(1960, 10, 10));
        profile.setSpecialization(Stream.of("Database Admin", "Fullstack", "DevOps").collect(Collectors.toList()));
        profile.setContactInfo(Stream.of("tel: 9999-99-999", "email: bolkonskiy@mail").collect(Collectors.toList()));
        profileService.updateProfile(profile);

        assertEquals(profile, profileService.getProfileByUserId(2L));

    }

    @Test
    void getProfileByUserIdTest(){
        Profile profile = profileService.getProfileByUserId(3L);
        assertEquals(3, profile.getId());
    }

    @Test
    void negativeGetProfileByUserIdTest(){
        profileService.getProfileByUserId(0L);
        assertThrows(NullPointerException.class, ()->{
            throw new NullPointerException();
        });
    }
}