package com.javamentor.backend.service;

import com.javamentor.backend.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    List<User> getAllByRoles(String roleName);

    boolean existsById(Long id);

    User getUserById(Long id);

    boolean existsByUsername(String username);

    User getUserByUsername(String username);

    boolean addUser(User user);

    boolean editUser(User user);
}