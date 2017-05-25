package io.awilson.timein.domain;

import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Log
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;
    private String instructor;
    private String course;

    public String getFullName() {
        return firstName + " " + this.lastName;
    }
}
