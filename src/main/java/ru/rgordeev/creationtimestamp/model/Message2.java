package ru.rgordeev.creationtimestamp.model;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
public class Message2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "payload", columnDefinition = "text")
    private String payload;

    @Column(name = "created", columnDefinition = "timestamp with time zone")
    @CreationTimestamp
    private ZonedDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message2 message2 = (Message2) o;
        return id == message2.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(37);
    }
}
