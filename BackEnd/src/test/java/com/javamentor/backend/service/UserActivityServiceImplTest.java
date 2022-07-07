package com.javamentor.backend.service;

import com.javamentor.backend.model.NameActivityType;
import com.javamentor.backend.model.User;
import com.javamentor.backend.repository.ActivityTypeRepository;
import com.javamentor.backend.repository.UserActivityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserActivityServiceImplTest {

    private final UserActivityService userActivityService;
    private final ActivityTypeService activityTypeService;
    private final ActivityTypeRepository activityTypeRepository;
    private final UserActivityRepository userActivityRepository;
    private final UserService userService;

    @Autowired
    public UserActivityServiceImplTest(UserActivityService userActivityService,
                                       ActivityTypeService activityTypeService,
                                       ActivityTypeRepository activityTypeRepository,
                                       UserActivityRepository userActivityRepository,
                                       UserService userService) {
        this.userActivityService = userActivityService;
        this.activityTypeService = activityTypeService;
        this.activityTypeRepository = activityTypeRepository;
        this.userActivityRepository = userActivityRepository;
        this.userService = userService;
    }

    @Test
    void createUserActivityTest() {
        int countUserActivity = userActivityRepository.findAll().size();

        User newUser = new User();
        newUser.setUsername("Mark");
        newUser.setEmail("mark@email.com");
        newUser.setPassword("test");
        userService.addUser(newUser);
        User thisUser = userService.getUserById(newUser.getId());

        userActivityService.createUserActivity(thisUser, NameActivityType.CREATE_COMMENT, true);

        assertEquals(countUserActivity + 1, userActivityRepository.findAll().size());
    }

    @Test
    void negativeCreateUserActivityTest() {
        int countUserActivity = userActivityRepository.findAll().size();
        User newUser = new User();
        userActivityService.createUserActivity(newUser, NameActivityType.CREATE_COMMENT, true);

        assertNotEquals(countUserActivity + 1, userActivityRepository.findAll().size());
    }

    @Test
    void existsByUserIdTest() {
        assert (userActivityService.existsUserActivitiesByUserId(1));
    }

    @Test
    void negativeExistsByUserIdTest() {
        assert (!userActivityService.existsUserActivitiesByUserId(-5));
    }

    @Test
    void findByActivityTypeTest() {
        int countUserActivity = userActivityService.findByActivityType(NameActivityType.CREATE_TOPIC).size();

        User newUser = new User();
        newUser.setUsername("Mark2");
        newUser.setEmail("mark2@email.com");
        newUser.setPassword("test");
        userService.addUser(newUser);
        User thisUser = userService.getUserById(newUser.getId());

        userActivityService.createUserActivity(thisUser, NameActivityType.CREATE_TOPIC, true);

        assertEquals(countUserActivity + 1, userActivityService.findByActivityType(NameActivityType.CREATE_TOPIC).size());
    }

    @Test
    void NegativeFindByActivityTypeTest() {
        int countUserActivity = userActivityService.findByActivityType(NameActivityType.CREATE_TOPIC).size();

        User newUser = new User();
        newUser.setUsername("Mark3");
        newUser.setEmail("mark3@email.com");
        newUser.setPassword("test");
        userService.addUser(newUser);
        User thisUser = userService.getUserById(newUser.getId());

        userActivityService.createUserActivity(thisUser, NameActivityType.CREATE_TOPIC, true);

        assertNotEquals(countUserActivity, userActivityService.findByActivityType(NameActivityType.CREATE_TOPIC).size());
    }

    @Test
    void findByUserId() {
        User newUser = new User();
        newUser.setUsername("Mark4");
        newUser.setEmail("mark4@email.com");
        newUser.setPassword("test");
        userService.addUser(newUser);
        User thisUser = userService.getUserById(newUser.getId());

        userActivityService.createUserActivity(thisUser, NameActivityType.CREATE_TOPIC, true);

        int countUseActivityAfter = userActivityService.findByUserId(thisUser.getId()).size();
        userActivityService.createUserActivity(thisUser, NameActivityType.CREATE_TOPIC, true);
        assertEquals(countUseActivityAfter + 1, userActivityService.findByUserId(thisUser.getId()).size());
    }

    @Test
    void negativeFindByUserId() {
        User newUser = new User();
        newUser.setUsername("Mark3");
        newUser.setEmail("mark3@email.com");
        newUser.setPassword("test");
        userService.addUser(newUser);
        User thisUser = userService.getUserById(newUser.getId());

        userActivityService.createUserActivity(thisUser, NameActivityType.CREATE_TOPIC, true);

        int countUseActivityAfter = userActivityService.findByUserId(thisUser.getId()).size();
        assertNotEquals(countUseActivityAfter + 1, userActivityService.findByUserId(thisUser.getId()).size());
    }
}