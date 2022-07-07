package com.javamentor.backend.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "topics_authors",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "authors_id")
    )
    private Set<User> authors;

    @Column(name = "published",nullable = false)
    private LocalDateTime published;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "topics_votes",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "vote_id")
    )
    private Set<User> votes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "topics_views",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "views_id")
    )
    private Set<User> views;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_bookmarks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bookmark_id"))
    private Set<User> userBookmarks;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Topic topic = (Topic) o;
        return Objects.equals(id, topic.id)
                && Objects.equals(title, topic.title)
                && Objects.equals(published, topic.published);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, published);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", published=" + published +
                ", content=" + content +
                ", votes=" + votes +
                ", views=" + views +
                '}';
    }
}