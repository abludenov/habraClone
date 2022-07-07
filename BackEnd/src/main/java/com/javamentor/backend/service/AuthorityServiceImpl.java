package com.javamentor.backend.service;

import com.javamentor.backend.model.Authority;
import com.javamentor.backend.model.Role;
import com.javamentor.backend.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;
    private RoleService roleService;
    private final String DEFAULT_AUTHORITY = "canReadTopics";

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public boolean existsAuthorityByName(String name) {
        return authorityRepository.existsByName(name);
    }

    @Override
    public List<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority getAuthorityByName(String name) {
        return authorityRepository.findByName(name);
    }

    @Override
    public void createAuthority(Authority authority) {
        if (!existsAuthorityByName(authority.getName())) {
            authorityRepository.save(authority);
        }
    }

    @Override
    public void editAuthority(Authority authority) {
        if (existsAuthorityByName(authority.getName())) {
            authorityRepository.save(authority);
        }
    }

    /**
     * Метод для удаления Authority (разрешений для ролей)
     *
     * Единственное разрешение, которое нельзя удалять, — canReadTopics:
     * - оно используется в RoleServiceImpl как базовое значение для новой/редактируемой/заменяемой после удаления роли,
     * - для любых пользователей (в т.ч. незарегистрированных) должно быть хотя бы одно разрешение.
     *
     * Работа метода:
     * Если разрешение существует и не равняется canReadTopics, достаём список ролей с нужным разрешением
     * и из сета в каждой роли удаляем это разрешение.
     *
     * На вход принимается один аргумент:
     * @param name - имя разрешения, которое требуется удалить.
     */
    @Override
    public void deleteAuthorityByName(String name) {
        if (!DEFAULT_AUTHORITY.equals(name) && existsAuthorityByName(name)) {
            List<Role> rolesWithAuthority = roleService.findAllByAuthorities(name);

            if (rolesWithAuthority.size() > 0) {
                for (Role role : rolesWithAuthority) {
                    Set<Authority> authoritySet = role.getAuthorities();
                    authoritySet.removeIf(auth -> auth.getName().equals(name));
                    roleService.editRole(role.getName());
                }
            }

            authorityRepository.deleteByName(name);
        }
    }
}
