package com.javamentor.backend.service;

import com.javamentor.backend.model.Karma;
import com.javamentor.backend.model.Role;
import com.javamentor.backend.model.User;
import com.javamentor.backend.repository.KarmaRepository;
import com.javamentor.backend.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class KarmaServiceImpl implements KarmaService {

    private final KarmaRepository karmaRepository;
    private final RoleRepository roleRepository;

    public KarmaServiceImpl(KarmaRepository karmaRepository, RoleRepository roleRepository) {
        this.karmaRepository = karmaRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Karma karma) {
        karmaRepository.save(karma);
    }

    @Override
    public List<Karma> findKarmaHistory(User user) {
        return karmaRepository.findKarmaHistory(user);
    }

    @Override
    public Double findActualKarma(User user) {
        return karmaRepository.findActualKarma(user);
    }

    //кому менял карму текущий юзер
    @Override
    public List<User> findEditedUsersByUser(User user) {
        return karmaRepository.findEditedUsersByUser(user);
    }

    @Override
    public List<String> findAllInteractionByUser(User editor, User edited) {
        return karmaRepository.findAllInteractionByUser(editor, edited);
    }

    /**
     * Метод повышения кармы. Один пользователь (editor) может повысить
     * карму другому пользователю (edited) один раз за всё время. Кроме того
     * у пользователя ограниченное количество изменений кармы в сутки, точное
     * количество зависит от роли.
     *
     * Метод проверяет, есть ли у пользователя полномочие (authority) на редактирование
     * кармы другому пользователю.
     *
     * Затем идёт проверка, не повышал ли пользователь этой же жертве карму уже.
     *
     * Затем проверка на наличие доступных изменений кармы.
     *
     * В случае если все три проверки дали положительный результат - создаётся новый
     * объект Karma, запрашивается предыдущее значение и увеличивается на 0.1
     *
     * @param editor - User, который меняет карму другому пользователю
     * @param edited - User, которому меняют карму
     */

    @Override
    public void increaseKarma(User editor, User edited) {
        String reason = "INCREASE";
        String authorityName = "canIncreaseKarma";

        if ((checkRole(editor, authorityName)) && checkAlreadyChanged(reason, editor, edited)) {
            createKarma(reason, 0.1, editor, edited);
        }
    }

    /**
     * Метод понижения кармы. Один пользователь (editor) может понизить
     * карму другому пользователю (edited) один раз за всё время. Кроме того
     * у пользователя ограниченное количество изменений кармы в сутки, точное
     * количество зависит от роли.
     *
     * Метод проверяет, есть ли у пользователя полномочие (authority) на редактирование
     * кармы другому пользователю.
     *
     * Затем идёт проверка, не понишал ли пользователь этой же жертве карму уже.
     *
     * Затем проверка на наличие доступных изменений кармы.
     *
     * В случае если все три проверки дали положительный результат - создаётся новый
     * объект Karma, запрашивается предыдущее значение и уменьшается на 0.1
     *
     * @param editor - User, который меняет карму другому пользователю
     * @param edited - User, которому меняют карму
     */

    @Override
    public void decreaseKarma(User editor, User edited) {
        String reason = "DECREASE";
        String authorityName = "canDecreaseKarma";

        if ((checkRole(editor, authorityName)) && checkAlreadyChanged(reason, editor, edited)) {
            createKarma(reason, -0.1, editor, edited);
        }
    }

    /**
     * Метод обнуления кармы. Её можно обнулить один раз за всё время.
     * Технически это выглядит как изменение кармы самому себе, т.е.
     * запись в таблице будет содержать одного и того же юзера.
     *
     * Чтоб обнулить карму нужно передать в метод User'а
     *
     * @param user - пользователь, который обнуляет себе карму
     */

    @Override
    public void nullifyKarma(User user) {
        String reason = "NULLIFY";
        List<String> interaction = findAllInteractionByUser(user, user);
        if (!interaction.contains(reason)) {
            Karma karma = new Karma();
            karma.setValue(0.0);
            karma.setReason(reason);
            karma.setEditedUser(user);
            karma.setEditorUser(user);
            karmaRepository.save(karma);
        }
    }

    private boolean checkRole(User editor, String authorityName) {
        Set<Role> userRoles = editor.getRoles(); // список ролей пользователя-автора
        List<Role> authorityRoles = roleRepository.findAllByAuthorities(authorityName); // список ролей с разрешением на увеличение кармы
        for (Role r : userRoles) {
            if (authorityRoles.contains(r)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkAlreadyChanged(String reason, User editor, User edited) {
        List<String> interaction = findAllInteractionByUser(editor, edited);
        if (!interaction.contains(reason)) {
            return true;
        } else {
            return false;
        }
    }

    private void createKarma(String reason, Double value, User editor, User edited) {
        Karma karma = new Karma();
        karma.setValue(findActualKarma(edited) + value);
        karma.setReason(reason);
        karma.setEditedUser(edited);
        karma.setEditorUser(editor);
        karmaRepository.save(karma);
    }

}
