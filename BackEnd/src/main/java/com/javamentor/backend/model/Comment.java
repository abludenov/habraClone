package com.javamentor.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "published", nullable = false)
    private LocalDateTime published;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "comments_positive_Votes",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> positiveVotes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "comments_negative_Votes",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> negativeVotes;


    @Column(name = "is_moderate")
    private boolean moderate = false;

    @Column(name = "is_removed")
    private boolean removed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_comment_id")
    private Comment mainComment;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(published, comment.published);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content=" + content +
                ", published=" + published +
                ", moderate=" + moderate +
                ", topicId=" + topic +
                ", userId=" + user +
                ", mainComment=" + mainComment +
                '}';
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(id, published);
    }
}