package io.awilson.timein.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(of = {"id"})
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    @Column
    private Instant timeStart = Instant.now();

    @Column
    private Instant timeEnd;

    @ManyToOne
    private Student student;

    /**
     * Return the length of this session.
     * @return
     */
    public Duration getDuration(){
        if(timeEnd == null) {
            return Duration.between(timeStart, Instant.now());
        } else {
            return Duration.between(timeStart, timeEnd);
        }
    }

    public void close() {
        timeEnd = Instant.now();
    }
}
