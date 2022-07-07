package com.javamentor.backend.service;

import com.javamentor.backend.model.Authority;
import com.javamentor.backend.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    List<Role> findAll();

    List<Role> findAllByAuthorities(String authorityName);

    boolean existsByName(String name);

    Role getRoleByName(String name);

    boolean addRole(String name, Set<Authority> authorities);

    boolean editRole(String name);

    boolean deleteRole(String roleName);

    boolean changeRoleForUsers(Long newRoleId, Long currentRoleId);

    Long usersHaveRole(Long roleId);
}