package com.javamentor.backend.service;

import com.javamentor.backend.model.Comment;
import com.javamentor.backend.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        comment.setRemoved(true);
        comment.setContent(null);
        comment.setUser(null);
        comment.setNegativeVotes(null);
        comment.setPositiveVotes(null);
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllCommentsByTopicId(Long id) {
        return commentRepository.getCommentsByTopicId(id);
    }

    @Override
    public List<Comment> getCommentsByUserId(Long id) {
        return commentRepository.getCommentsByUserId(id);
    }


    @Override
    public void editComment(Comment comment) {
        if (!comment.isRemoved()) {
            LocalDateTime today = LocalDateTime.now();
            if (today.getMinute() < comment.getPublished().plusDays(1L).getMinute()) {
                commentRepository.save(comment);
            }
        }
    }

    @Override
    public List<Comment> getAllByModerateIsFalse() {
        return commentRepository.getAllByModerateIsFalse();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.getCommentById(id);
    }
}