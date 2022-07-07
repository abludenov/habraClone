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
@Table(name = "karma")
public class Karma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Double value;

    @Column(name = "reason")
    private String reason;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "editor_user_id")
    private User editorUser;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "edited_user_id")
    private User editedUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Karma karma = (Karma) o;
        return Objects.equals(id, karma.id)
                && Objects.equals(value, karma.value)
                && Objects.equals(reason, karma.reason)
                && Objects.equals(editorUser, karma.editorUser)
                && Objects.equals(editedUser, karma.editedUser);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(id, value, reason, editorUser, editedUser);
    }

    @Override
    public String toString() {
        return "Karma{" +
                "id=" + id +
                ", value=" + value +
                ", reason='" + reason + '\'' +
                ", editorUser=" + editorUser +
                ", editedUser=" + editedUser +
                '}';
    }
}