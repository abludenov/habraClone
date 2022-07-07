package com.javamentor.backend.repository;

import com.javamentor.backend.model.Karma;
import com.javamentor.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KarmaRepository extends JpaRepository<Karma, Long> {

    @Query("select k from Karma k where k.editedUser=:user")
    List<Karma> findKarmaHistory(User user);

    @Query("select k.value from Karma k where k.id = (select max(k.id) from k where k.editedUser=:user)")
    Double findActualKarma(User user);

    @Query("select k.editedUser from Karma k where k.editorUser=:user")
    List<User> findEditedUsersByUser(User user);

    @Query("select k.reason from Karma k where k.editorUser=:editor and k.editedUser=:edited")
    List<String> findAllInteractionByUser(User editor, User edited);
}