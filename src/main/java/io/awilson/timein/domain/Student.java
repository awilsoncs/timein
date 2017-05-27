package io.awilson.timein.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ToString(exclude = {"instructor", "sessions"})
@EqualsAndHashCode(exclude = {"instructor"})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Version
    private Integer version;
    private String firstName;
    private String lastName;
    @ManyToOne
    private Instructor instructor;
    @ManyToOne
    private Course course;
    @OneToMany(mappedBy = "student")
    private List<Session> sessions;
    @OneToOne
    private Session currentSession;

    /**
     * Does the student have any active sessions?
     * @return
     */
    public Boolean anyActive() {
        return sessions.stream()
                .anyMatch(x -> x.getEnd() == null);
    }

    public String getFullName() {
        return firstName + " " + this.lastName;
    }
}
