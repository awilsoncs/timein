package io.awilson.timein.domain;

import lombok.*;

import javax.persistence.*;
import java.time.*;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.temporal.TemporalAdjusters.next;

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

    /**
     * Get date that the session started on.
     * @return
     */
    @Transient
    public LocalDate getDate() {
        return LocalDateTime.ofInstant(timeStart, ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Return the date of the Friday following this Session.
     */
    @Transient
    public LocalDate getWeekEnd() {
        return getDate().with(next(FRIDAY));
    }

    /**
     * End the session now.
     */
    public void close() {
        timeEnd = Instant.now();
    }
}
