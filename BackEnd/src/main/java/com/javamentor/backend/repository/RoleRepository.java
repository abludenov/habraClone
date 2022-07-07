package com.javamentor.backend.repository;

import com.javamentor.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select distinct r from Role r join fetch r.authorities ra where ra.name = :authorityName")
    List<Role> findAllByAuthorities(@Param("authorityName") String authorityName);

    Role getRoleByName(String name);

    boolean existsByName(String name);

    @Modifying
    @Query(nativeQuery = true,
            value = "update user_roles set role_id = :newRoleId " +
                    "where exists(select roles.id from roles where roles.id = :newRoleId) and role_id = :currentRoleId")
    void changeRoleForUsers(@Param("newRoleId") Long newRoleId, @Param("currentRoleId") Long currentRoleId);

    @Query(nativeQuery = true, value = "select count(user_id) from user_roles where role_id = :roleId")
    Long usersHaveRole(@Param("roleId") Long roleId);
}