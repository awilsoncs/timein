package io.awilson.timein.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ToString(exclude = {"students"})
@EqualsAndHashCode(exclude = {"students"})
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Version
    private Integer version;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy = "instructor")
    private List<Student> students;
    public String getFullName() {
        return firstName + " " + this.lastName;
    }
}
