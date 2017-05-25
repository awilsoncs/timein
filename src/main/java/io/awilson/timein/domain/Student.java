package io.awilson.timein.domain;

import lombok.*;
import lombok.extern.java.Log;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.*;

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

    public String getFullName() {
        return firstName + " " + this.lastName;
    }

}
