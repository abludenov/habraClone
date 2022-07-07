package com.javamentor.backend.service;

import com.javamentor.backend.model.Comment;

import java.util.List;


public interface CommentService {

    void saveComment(Comment comment);

    void deleteComment(Comment comment);

    List<Comment> getAllCommentsByTopicId(Long id);

    List<Comment> getCommentsByUserId(Long id);

    void editComment(Comment comment);

    List<Comment> getAllByModerateIsFalse();

    Comment getCommentById(Long id);
}
