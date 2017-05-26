package io.awilson.timein.domain;

import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Log
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Version
    private Integer version;
    private String firstName;
    private String lastName;
    private String instructor;
    private String course;
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
