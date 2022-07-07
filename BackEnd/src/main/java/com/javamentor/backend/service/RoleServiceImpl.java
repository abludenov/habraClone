package com.javamentor.backend.service;

import com.javamentor.backend.model.Authority;
import com.javamentor.backend.model.Role;
import com.javamentor.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final AuthorityService authorityService;
    private final String DEFAULT_AUTHORITY = "canReadTopics";

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, AuthorityService authorityService) {
        this.roleRepository = roleRepository;
        this.authorityService = authorityService;
    }

    @Override
    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findAllByAuthorities(String authorityName) {
        return roleRepository.findAllByAuthorities(authorityName);
    }

    /**
     * Метод для добавления в базу данных нового User.
     *
     * На вход принимает два аргумента:
     * @param name - имя новой Role.
     * @param authorities - множество Authority новой Role.
     * @return true, если Role успешно добавлена.
     */
    /*
     * TODO:
     *  Предполагается, что в будущем на вход будет приниматься объект типа Role, преобразованный из DTO_Role,
     *  поэтому, входные аргументы метода могут измениться.
     * */
    @Override
    public boolean addRole(String name, Set<Authority> authorities) {

        Role newRole = new Role();
        newRole.setName(name);

        if (newRole.getName() != null) {
            newRole.setAuthorities(authorities);

            if (newRole.getAuthorities().isEmpty()) {
                newRole.getAuthorities().add(authorityService.getAuthorityByName(DEFAULT_AUTHORITY));
            }

            Role addedRole = roleRepository.save(newRole);
            return existsByName(addedRole.getName());
        } else {
            return false;
        }
    }

    /**
     * Метод для редактирования существующей Role.
     *
     * На вход принимает один аргумент:
     * @param name - имя Role, которая редактируется.
     * @return true, если Role была успешно отредактирована.
     */
    /*
     * TODO:
     *  Предполагается, что в будущем на вход будет приниматься объект типа Role, преобразованный из DTO_Role,
     *  поэтому, имеет смысл добавить проверку на содержимое объектов User (из DTO_Role)
     *  и Role (из базы данных по name или id, идентичному Role из DTO_Role).
     *  Если есть различия в их содержимом, то метод будет возвращает true, если объекты идентичны, то false,
     *  как при невалидном содержимом объекта User (из DTO_Role).
     * */
    @Override
    public boolean editRole(String name) {

        if (existsByName(name)) {
            Role existentRole = getRoleByName(name);

            if ((existentRole.getName() != null) && !name.equals(existentRole.getName())) {
                getRoleByName(name).setName(name);
            } else {
                return false;
            }

            if (getRoleByName(existentRole.getName()).getAuthorities().isEmpty()) {
                getRoleByName(existentRole.getName()).getAuthorities().add(authorityService.getAuthorityByName(DEFAULT_AUTHORITY));
            }

            roleRepository.save(existentRole);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод для удаления существующей Role.
     *
     * На вход принимает один аргумент:
     * @param roleName - имя Role, которая удаляется.
     * @return true, если Role была успешно удалена.
     *
     * Внутри этого метода производится дополнительная проверка через вызов служебного метода usersHaveRole, который
     * проверяет есть ли у удаляемой Role связи с User. Если по каким-то причинам эти связи есть/остались, то
     * создаётся новая Role - "заглушка" с последующим вызовом другого служебного метода changeRoleForUsers,
     * который уберёт связи удаляемой Role с User и создаст их с Role - "заглушкой".
     */
    @Override
    public boolean deleteRole(String roleName) {

        if (existsByName(roleName)) {
            Role thisRole = roleRepository.getRoleByName(roleName);

            if (usersHaveRole(thisRole.getId()) > 0) {
                Role guest = new Role();
                guest.setName("ROLE_GUEST");
                Set<Authority> guestAuthorities = new HashSet<>();
                guestAuthorities.add(authorityService.getAuthorityByName(DEFAULT_AUTHORITY));
                guest.setAuthorities(guestAuthorities);

                roleRepository.save(guest);

                changeRoleForUsers(guest.getId(), thisRole.getId());
            }

            roleRepository.delete(thisRole);

            return !existsByName(roleName);
        } else {
            return false;
        }
    }

    /**
     * Служебный метод для изменения связей Role с User.
     *
     * На вход принимает два аргумента:
     * @param newRoleId - id Role, которую нужно связать с User.
     * @param currentRoleId - id Role, у которой нужно разорвать связь со всеми User.
     * @return true, если связи были успешно изменены.
     */
    @Override
    public boolean changeRoleForUsers(Long newRoleId, Long currentRoleId) {
        roleRepository.changeRoleForUsers(newRoleId, currentRoleId);

        return (usersHaveRole(newRoleId) > 0) && (usersHaveRole(currentRoleId) == 0);
    }

    /**
     * Служебный метод для проверки наличия связей у конкретной Role с User,
     * возвращающий количество User имеющих связь с данной Role.
     *
     * На вход принимает один аргумент:
     * @param roleId - id Role, для которой нужно выполнить проверку.
     * @return Long count >= 0.
     */
    @Override
    public Long usersHaveRole(Long roleId) {
        return roleRepository.usersHaveRole(roleId);
    }
}
