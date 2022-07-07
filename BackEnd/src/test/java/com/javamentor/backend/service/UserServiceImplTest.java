package com.javamentor.backend.service;

import com.javamentor.backend.model.Role;
import com.javamentor.backend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserServiceImplTest(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Test
    void addUserTest() {
        User newUser = new User();
        newUser.setUsername("Dima");
        newUser.setEmail("dima@email.com");
        newUser.setPassword("test");

        boolean is_added = userService.addUser(newUser);

        User thisUser = userService.getUserById(newUser.getId());

        assertEquals(thisUser.getUsername().length(), newUser.getUsername().length());

        List<String> roleList = new ArrayList<>();
        roleList.add("ROLE_USER");

        List<String> userRoleList = newUser.getRoles().stream().map(Role::getName).collect(Collectors.toList());

        assertLinesMatch(roleList, userRoleList);

        assertEquals(0, newUser.getFollowers().size());
        assertEquals(0, newUser.getFollowings().size());
        assertEquals(0, newUser.getInvitedUsers().size());
        assertEquals(0, newUser.getBadges().size());

        assertTrue(is_added);
    }

    @Test
    void negativeAddUserTest() {
        User newUser = new User();
        newUser.setUsername(null);
        newUser.setEmail(null);
        newUser.setPassword(null);

        boolean is_added = userService.addUser(newUser);

        assertFalse(is_added);
    }

    @Test
    void findAllTest() {
        List<User> userList = userService.findAll();

        assertEquals(3, userList.size());
    }

    @Test
    void negativeFindAllTest() {
        List<User> userList = userService.findAll();

        assertNotEquals(0, userList.size());
    }

    @Test
    void findAllByRolesTest() {
        List<User> userList = userService.getAllByRoles("ROLE_ADMIN");

        assertEquals(1, userList.size());
    }

    @Test
    void negativeFindAllByRolesTest() {
        List<User> userList = userService.getAllByRoles("ROLE_MENTOR");

        assertEquals(0, userList.size());
    }

    @Test
    void getUserByIdTest() {
        User user = userService.getUserById(2L);

        assertEquals(2, user.getId());
        assertEquals(1, user.getFollowers().size());
        assertEquals(1, user.getFollowings().size());
        assertEquals(1, user.getInvitedUsers().size());
    }

    @Test
    void negativeGetUserByIdTest() {
        User user = userService.getUserById(4L);

        assertThrows(NullPointerException.class, () -> {
            throw new NullPointerException();
        });
    }

    @Test
    void getUserByUsernameTest() {
        User user = userService.getUserByUsername("Andrey");

        assertEquals(2, user.getId());
    }

    @Test
    void negativeGetUserByUsernameTest() {
        User user = userService.getUserByUsername("Pikachu");

        assertThrows(NullPointerException.class, () -> {
            throw new NullPointerException();
        });
    }

    @Test
    void editUserTest() {

        User editUser = userService.getUserById(3L);
        editUser.setUsername("Alexander");

        Set<Role> newRoles = editUser.getRoles();
        Role newRole = roleService.getRoleByName("ROLE_ADMIN");
        newRoles.add(newRole);
        editUser.setRoles(newRoles);

        boolean is_edited = userService.editUser(editUser);

        User thisUser = userService.getUserByUsername("Alexander");

        assertEquals(thisUser.getUsername().length(), editUser.getUsername().length());
        assertEquals(thisUser.getRoles().size(), editUser.getRoles().size());
        assertTrue(is_edited);
    }

    @Test
    void negativeEditUserTest() {

        User editUser = userService.getUserById(3L);
        editUser.setUsername(null);
        editUser.setEmail(null);
        editUser.setPassword(null);

        boolean is_edited = userService.editUser(editUser);

        assertFalse(is_edited);
    }

    @Test
    void existsByIdTest() {
        boolean user = userService.existsById(1L);

        assertTrue(user);
    }

    @Test
    void negativeExistsByIdTest() {
        boolean user = userService.existsById(999L);

        assertFalse(user);
    }

    @Test
    void existsByUsernameTest() {
        boolean user = userService.existsByUsername("Ilya");

        assertTrue(user);
    }

    @Test
    void negativeExistsByUsernameTest() {
        boolean user_1 = userService.existsByUsername("Grzegorz");
        boolean user_2 = userService.existsByUsername("Boleslaw");

        assertFalse(user_1);
        assertFalse(user_2);
    }
}