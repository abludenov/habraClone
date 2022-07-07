package com.javamentor.backend.service;

import com.javamentor.backend.model.Authority;
import com.javamentor.backend.model.Role;
import com.javamentor.backend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RoleServiceImplTest {

    private final RoleService roleService;
    private final AuthorityService authorityService;
    private final UserService userService;

    @Autowired
    public RoleServiceImplTest(RoleService roleService, AuthorityService authorityService, UserService userService) {
        this.roleService = roleService;
        this.authorityService = authorityService;
        this.userService = userService;
    }

    @Test
    void addRoleTest() {

        boolean is_added = roleService.addRole("ROLE_MENTOR", new HashSet<>());

        assertEquals(4, roleService.findAll().size());

        Role thisRole = roleService.getRoleByName("ROLE_MENTOR");

        List<String> authoritiesList = new ArrayList<>();
        authoritiesList.add("canReadTopics");

        List<String> roleAuthoritiesList = thisRole.getAuthorities().stream()
                .map(Authority::getName)
                .collect(Collectors.toList());

        assertLinesMatch(authoritiesList, roleAuthoritiesList);
        assertTrue(is_added);
    }

    @Test
    void negativeAddRoleTest() {
        boolean is_added = roleService.addRole(null, new HashSet<>());

        assertFalse(is_added);
    }

    @Test
    void editRoleTest() {
        Role editRole = roleService.getRoleByName("ROLE_USER");
        editRole.setName("ROLE_TEAMLEAD");

        Set<Authority> newAuthorities = editRole.getAuthorities();
        Authority newAuthority = authorityService.getAuthorityByName("allInclusive");
        newAuthorities.add(newAuthority);
        editRole.setAuthorities(newAuthorities);

        roleService.editRole(editRole.getName());

        Role thisRole = roleService.getRoleByName("ROLE_TEAMLEAD");

        assertEquals(thisRole.getName().length(), editRole.getName().length());
        assertEquals(thisRole.getAuthorities().size(), editRole.getAuthorities().size());
    }

    @Test
    void negativeEditRoleTest() {
        Role editRole = roleService.getRoleByName("ROLE_USER");
        editRole.setName(null);

        boolean is_edited = roleService.editRole(editRole.getName());

        assertFalse(is_edited);
    }

    @Test
    void deleteRoleTest() {
        int countRolesBefore = roleService.findAll().size();

        boolean is_deleted = roleService.deleteRole("ROLE_USER");

        int countRolesAfter = roleService.findAll().size();

        Role guest = roleService.getRoleByName("ROLE_GUEST");

        assertEquals(3, countRolesBefore);
        assertEquals("ROLE_GUEST", guest.getName());
        assertEquals(3, countRolesAfter);
        assertTrue(is_deleted);
    }

    @Test
    void negativeDeleteRoleTest() {
        int countRolesBefore = roleService.findAll().size();

        boolean is_deleted = roleService.deleteRole("ROLE_MENTOR");

        int countRolesAfter = roleService.findAll().size();

        assertEquals(3, countRolesBefore);
        assertFalse(is_deleted);
        assertEquals(3, countRolesAfter);
    }

    @Test
    void getRoleByNameTest() {
        Role role = roleService.getRoleByName("ROLE_USER");

        assertEquals("ROLE_USER", role.getName());
    }

    @Test
    void negativeGetRoleByNameTest() {
        Role role = roleService.getRoleByName("ROLE_MENTOR");

        assertThrows(NullPointerException.class, () -> {
            throw new NullPointerException();
        });
    }

    @Test
    void findAllTest() {
        List<Role> roleList = roleService.findAll();

        assertEquals(3, roleList.size());
    }

    @Test
    void negativeFindAllTest() {
        List<Role> roleList = roleService.findAll();

        assertNotEquals(0, roleList.size());
    }

    @Test
    void findAllByAuthoritiesTest() {
        List<Role> roleList = roleService.findAllByAuthorities("canReadTopics");

        assertEquals(1, roleList.size());
    }

    @Test
    void negativeFindAllByAuthoritiesTest() {
        List<Role> roleList = roleService.findAllByAuthorities("totalDestruction");

        assertEquals(0, roleList.size());
    }

    @Test
    void changeRoleForUsersTest() {
        List<User> usersWithRoleBefore = userService.getAllByRoles("ROLE_USER");
        assertEquals(1, usersWithRoleBefore.size());

        boolean is_changed = roleService.changeRoleForUsers(2L, 1L);

        List<User> usersWithRoleAfter = userService.getAllByRoles("ROLE_USER");

        assertEquals(2, usersWithRoleAfter.size());
        assertTrue(is_changed);
    }

    @Test
    void negativeChangeRoleForUsersTest() {
        List<User> usersWithRoleBefore = userService.getAllByRoles("ROLE_USER");
        assertEquals(1, usersWithRoleBefore.size());

        boolean is_changed = roleService.changeRoleForUsers(4L, 2L);

        List<User> usersWithRoleAfter = userService.getAllByRoles("ROLE_USER");

        assertNotEquals(2, usersWithRoleAfter.size());
        assertFalse(is_changed);
    }

    @Test
    void usersHaveRoleTest() {
        Long users = roleService.usersHaveRole(1L);
        List<User> userList = userService.getAllByRoles("ROLE_USER");

        assertEquals(userList.size(), users);
    }

    @Test
    void negativeUsersHaveRoleTest() {
        Long users = roleService.usersHaveRole(4L);
        List<User> userList = userService.getAllByRoles("ROLE_USER");

        assertNotEquals(userList.size(), users);
    }

    @Test
    void existsByNameTest() {
        boolean is_exist = roleService.existsByName("ROLE_USER");

        assertTrue(is_exist);
    }

    @Test
    void negativeExistsByNameTest() {
        boolean is_exist = roleService.existsByName("ROLE_MENTOR");

        assertFalse(is_exist);
    }
}
