package com.javamentor.backend.service;

import com.javamentor.backend.model.Karma;
import com.javamentor.backend.model.User;

import java.util.List;

public interface KarmaService {

    void save(Karma karma);

    List<Karma> findKarmaHistory(User user);

    Double findActualKarma(User user);

    List<User> findEditedUsersByUser(User user);

    List<String> findAllInteractionByUser(User editor, User edited);

    void increaseKarma(User editor, User edited);

    void decreaseKarma(User editor, User edited);

    void nullifyKarma(User user);
}
