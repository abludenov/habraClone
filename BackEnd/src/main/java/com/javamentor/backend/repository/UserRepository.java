package com.javamentor.backend.repository;

import com.javamentor.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select distinct u from User u join fetch u.roles ur where ur.name=:roleName")
    List<User> getAllByRoles(@Param("roleName") String roleName);

    User getUserByUsername(String username);

    User getUserById(Long id);

    boolean existsById(Long id);

    boolean existsByUsername(String username);
}