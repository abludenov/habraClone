package com.javamentor.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_activities")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activity_date_time")
    private LocalDateTime lastActivity;

    @Column(name = "is_change_up")
    private Boolean changeUp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_types_id")
    private ActivityType activityType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserActivity that = (UserActivity) o;
        return Objects.equals(lastActivity, that.lastActivity)
                && Objects.equals(changeUp, that.changeUp)
                && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastActivity, changeUp, user);
    }

    @Override
    public String toString() {
        return "UserActivity{" +
                "id=" + id +
                ", lastActivity=" + lastActivity +
                ", changeUp=" + changeUp +
                ", userId=" + user +
                '}';
    }
}