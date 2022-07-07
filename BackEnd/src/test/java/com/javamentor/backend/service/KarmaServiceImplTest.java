package com.javamentor.backend.service;

import com.javamentor.backend.model.Karma;
import com.javamentor.backend.model.User;
import com.javamentor.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class KarmaServiceImplTest {

    @Autowired
    private KarmaServiceImpl karmaService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findKarmaHistory() {
        List<Karma> before = karmaService.findKarmaHistory(userRepository.getById(3L));
        Karma karmaOne = makeKarma(userRepository.getById(3L));
        karmaService.save(karmaOne);
        Karma karmaTwo = makeKarma(userRepository.getById(3L));
        karmaService.save(karmaTwo);
        Karma karmaThree = makeKarma(userRepository.getById(3L));
        karmaService.save(karmaThree);
        Karma karmaFour = makeKarma(userRepository.getById(1L));
        karmaService.save(karmaFour);
        Karma karmaFive = makeKarma(userRepository.getById(2L));
        karmaService.save(karmaFive);
        Karma karmaSix = makeKarma(userRepository.getById(3L));
        karmaSix.setValue(123.456);
        karmaService.save(karmaSix);
        List<Karma> after = karmaService.findKarmaHistory(userRepository.getById(3L));
        assertEquals(4, after.size() - before.size());
    }

    @Test
    void findActualKarma() {
        User user = userRepository.getById(1L);
        Karma karma = new Karma();
        Double karmaValue = 10.0;

        for (int i = 0; i<5; i++) {
            karmaValue = Math.random()*10;
            karma.setValue(karmaValue);
            karma.setReason("Test Iteration №" + i);
            karma.setEditedUser(user);
            karmaService.save(karma);
        }
        assertEquals(karmaValue, karmaService.findActualKarma(user));
    }

    @Test
    void findEditedUsersByUser() {
        Karma karmaOne = new Karma();
        karmaOne.setValue(0.123);
        karmaOne.setReason("Reason One");
        karmaOne.setEditedUser(userRepository.getById(3L));
        karmaOne.setEditorUser(userRepository.getById(3L));
        karmaService.save(karmaOne);

        Karma karmaTwo = new Karma();
        karmaTwo.setValue(3.2);
        karmaTwo.setReason("Reason Two");
        karmaTwo.setEditedUser(userRepository.getById(3L));
        karmaTwo.setEditorUser(userRepository.getById(3L));
        karmaService.save(karmaTwo);
        List<User> userList = karmaService.findEditedUsersByUser(userRepository.getById(3L));
        assertEquals(3, userList.size());
        assertEquals(1L, userList.get(0).getId());
        assertEquals(3L, userList.get(1).getId());
        assertEquals(3L, userList.get(2).getId());
    }

    @Test
    void increaseKarma() {
        /* Нужно написать тест после введения нужных authorities и ролей по следующему алгоритму:
        1. Дёргаем двух юзеров, editor и edited.
        2. Проверяем что в таблице нет записей о повышении кармы editor'ом edited'у.
        3. Если есть - дёргаем других юзеров или создаём.
        4. Проверяем актуальную карму edited'а методом findActualKarma(User user).
        5. Вызываем метод increaseKarma(User editor, User edited), куда подставляем наших юзеров
        6. Проверяем что актуальная карма изменилась на 0.1 в большую сторону (вызовом метода findActualKarma).
        7. Пробуем ещё раз вызвать increaseKarma(User editor, User edited), куда подставляем наших юзеров.
        8. Проверяем актуальную карму edited'а методом findActualKarma(User user).
        9. Убеждаемся что она осталась такой же, как в п.6, т.е. НЕ изменилась после выполнения п.7
        10. Готово, вы великолепны :)
        */
    }

    @Test
    void decreaseKarma() {
        /* Нужно написать тест после введения нужных authorities и ролей по следующему алгоритму:
        1. Дёргаем двух юзеров, editor и edited.
        2. Проверяем что в таблице нет записей о понижении кармы editor'ом edited'у.
        3. Если есть - дёргаем других юзеров или создаём.
        4. Проверяем актуальную карму edited'а методом findActualKarma(User user).
        5. Вызываем метод decreaseKarma(User editor, User edited), куда подставляем наших юзеров
        6. Проверяем что актуальная карма изменилась на 0.1 в меньшую сторону (вызовом метода findActualKarma).
        7. Пробуем ещё раз вызвать decreaseKarma(User editor, User edited), куда подставляем наших юзеров.
        8. Проверяем актуальную карму edited'а методом findActualKarma(User user).
        9. Убеждаемся что она осталась такой же, как в п.6, т.е. НЕ изменилась после выполнения п.7
        10. Готово, вы великолепны:)
        */
    }

    @Test
    void nullifyKarma() {
        User user = userRepository.getById(3L);
        List<String> interaction = karmaService.findAllInteractionByUser(user, user);
        String reason = "NULLIFY";
        int contains = 0;
        if (interaction.contains(reason)) {
            contains++;
        }
        assertEquals(0, contains); // проверяем что нет обнуления кармы для user
        Karma karma = new Karma();
        karma.setValue(0.0);
        karma.setReason(reason);
        karma.setEditedUser(user);
        karma.setEditorUser(user);
        karmaService.save(karma);

        interaction = karmaService.findAllInteractionByUser(user, user);
        if (interaction.contains(reason)) {
            contains++;
        }
        assertEquals(1, contains); // проверяем что произошло обнуление кармы
        assertEquals(0.0, karmaService.findActualKarma(user)); // проверяем что актуальная карма действительно равна 0.0
    }

    private Karma makeKarma(User user) {
        Karma karma = new Karma();
        karma.setReason("Reason " + (int) Math.random() * 100);
        karma.setValue(Math.random() * 100);
        karma.setEditedUser(user);
        return karma;
    }
}