package io.awilson.timein.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.Instant;

@Data
@Entity
@RequiredArgsConstructor
@EqualsAndHashCode(exclude={"student"})
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Version
    private Integer version;
    private Instant start = Instant.now();
    private Instant end;
    @ManyToOne
    private Student student;

    /**
     * Return the length of this session.
     * @return
     */
    public Duration getDuration(){
        if(end == null) {
            return Duration.between(start, Instant.now());
        } else {
            return Duration.between(start, end);
        }
    }

    public void close() {
        end = Instant.now();
    }
}
