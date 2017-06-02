package io.awilson.timein.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Duration;
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
                .anyMatch(x -> x.getTimeEnd() == null);
    }

    public String getFullName() {
        return firstName + " " + this.lastName;
    }

    public Duration getTime() {
        return sessions.stream()
                .map(Session::getDuration)
                .reduce((x, y) -> x.plus(y))
                .get();
    }
}
