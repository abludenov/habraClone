package com.javamentor.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "hubs")
public class Hub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    /**
     * TODO:
     *  Сделать иконки для хабов. В проекте уже есть/был референс - базовая аватарка пользователя.
     */

    @Column(name = "icon")
    private byte[] icon;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "hubs_tags",
            joinColumns = @JoinColumn(name = "hub_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "hubs_topics",
            joinColumns = @JoinColumn(name = "hub_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<Topic> topics;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_contributions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "contribution_id"))
    private Set<User> usersContributions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_hubs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hubs_id"))
    private Set<User> usersHubs;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hub hub = (Hub) o;
        return Objects.equals(id, hub.id) && Objects.equals(title, hub.title);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "Hub{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}