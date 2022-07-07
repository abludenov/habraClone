package com.javamentor.backend.service;

import com.javamentor.backend.model.Comment;
import com.javamentor.backend.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
class CommentServiceImplTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Test
    void saveCommentTest() {
        Comment comment = new Comment();
        comment.setPublished(LocalDateTime.now());
        comment.setContent("TEST");
        commentService.saveComment(comment);
        List<Comment> commentList = commentRepository.findAll();
        assertEquals(4, commentList.size());

    }

    @Test
    void getAllCommentsByTopicIdTest() {
        List<Comment> commentList = commentRepository.getCommentsByTopicId(1L);
        assertEquals(3, commentList.size());
    }

    @Test
    void getAllCommentsByUserAndIdTest() {
        List<Comment> commentList = commentRepository.getCommentsByUserId(2L);
        assertEquals(3, commentList.size());
    }

    @Test
    void getAllByModerateIsFalseTest() {
        List<Comment> commentList = commentRepository.getAllByModerateIsFalse();
        assertEquals(0, commentList.size());
    }
}