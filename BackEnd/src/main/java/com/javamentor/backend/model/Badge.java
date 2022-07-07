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
@Table(name = "badges")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "icon")
    private byte[] icon;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "badge_users",
            joinColumns = @JoinColumn(name = "badge_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private Set<User> usersHaveBadge;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Badge badge = (Badge) o;
        return Objects.equals(id, badge.id) && Objects.equals(title, badge.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
    @Override
    public String toString() {
        return "Badge{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}