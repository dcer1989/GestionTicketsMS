package com.hiberus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "showtimes")
@Getter
@Setter
public class Showtime {

    @Id
    private UUID id;

    private String room;
    private Instant time; // UTC

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;
}