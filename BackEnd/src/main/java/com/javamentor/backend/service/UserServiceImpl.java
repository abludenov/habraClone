package com.javamentor.backend.service;

import com.javamentor.backend.model.User;
import com.javamentor.backend.repository.RoleRepository;
import com.javamentor.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllByRoles(String roleName) {
        return userRepository.getAllByRoles(roleName);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    /**
     * Метод для добавления в базу данных нового User.
     *
     * На вход принимает один аргумент:
     * @param user - объект типа User. Этот объект проверяется на валидность.
     * @return true, если User успешно добавлен.
     */
    @Override
    public boolean addUser(User user) {

        if ((user.getUsername() == null) || (user.getEmail() == null) || (user.getPassword() == null)) {
            return false;
        }

        if (user.getFollowers() == null) {
            user.setFollowers(new HashSet<>());
        }

        if (user.getFollowings() == null) {
            user.setFollowings(new HashSet<>());
        }

        if (user.getInvitedUsers() == null) {
            user.setInvitedUsers(new HashSet<>());
        }

        if (user.getBadges() == null) {
            user.setBadges(new HashSet<>());
        }

        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }

        if (user.getRoles().isEmpty()) {
            user.getRoles().add(roleRepository.getRoleByName("ROLE_USER"));
        }

        User addedUser = userRepository.save(user);

        return existsById(addedUser.getId());
    }

    /**
     * Метод для редактирования существующего User.
     *
     * На вход принимает один аргумент:
     * @param user - объект типа User. Этот объект проверяется на валидность.
     * @return true, если User был успешно отредактирован.
     */
    /*
     * TODO:
     *  Предполагается, что в будущем на вход будет приниматься объект типа User, преобразованный из DTO_User,
     *  поэтому, имеет смысл добавить проверку на содержимое объектов User (из DTO_User) и User (из базы данных по id,
     *  идентичному User из DTO_User).
     *  Если есть различия в их содержимом, то метод будет возвращает true, если объекты идентичны, то false,
     *  как при невалидном содержимом объекта User (из DTO_User).
     * */
    @Override
    public boolean editUser(User user) {

        if ((user.getUsername() == null) || (user.getEmail() == null) || (user.getPassword() == null)) {
            return false;
        }

        if (existsById(user.getId())) {

            User existentUser = getUserById(user.getId());

            String inputUsername = user.getUsername();
            if (!inputUsername.equals(existentUser.getUsername())) {
                user.setUsername(inputUsername);
            }


            String inputEmail = user.getEmail();
            if (!inputEmail.equals(existentUser.getEmail())) {
                user.setEmail(inputEmail);
            }

            String inputPassword = user.getPassword();
            if (!inputPassword.equals(existentUser.getPassword())) {
                user.setPassword(inputPassword);
            }

            if (user.getRoles().isEmpty()) {
                user.getRoles().add(roleRepository.getRoleByName("ROLE_USER"));
            }

            userRepository.save(user);

            return true;
        } else {
            return false;
        }
    }
}