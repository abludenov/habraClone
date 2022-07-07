package com.javamentor.backend.repository;


import com.javamentor.backend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getCommentsByTopicId(Long id);

    List<Comment> getCommentsByUserId(Long id);

    List<Comment> getAllByModerateIsFalse();

    Comment getCommentById(Long id);
}