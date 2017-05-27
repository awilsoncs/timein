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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Version
    private Integer version;
    private String title;
    @OneToMany(mappedBy = "course")
    private List<Student> students;
}
