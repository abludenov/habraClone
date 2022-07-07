package com.javamentor.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "activity_types")
public class ActivityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private NameActivityType type;

    @Column(name = "change_coefficient", nullable = false)
    private Double changeCoefficient;

    @Override
    public String toString() {
        return "ActivityType{" +
                "id=" + id +
                ", type=" + type +
                ", changeCoefficient=" + changeCoefficient +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActivityType activityType = (ActivityType) o;
        return Objects.equals(type, activityType.type);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(type);
    }
}

