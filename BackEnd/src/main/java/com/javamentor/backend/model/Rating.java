package com.javamentor.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Double value;

    @Column(name = "reason")
    private String reason;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_activity_id")
    private UserActivity userActivity;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rating rating = (Rating) o;
        return Objects.equals(value, rating.value)
                && Objects.equals(reason, rating.reason)
                && Objects.equals(userActivity, rating.userActivity);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(value, reason, userActivity);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", value=" + value +
                ", reason='" + reason + '\'' +
                ", userActivity=" + userActivity +
                '}';
    }
}