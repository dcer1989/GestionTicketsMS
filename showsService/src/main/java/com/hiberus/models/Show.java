package com.hiberus.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shows")
@Entity
@Setter
@Getter
public class Show {

    @Id
    @GeneratedValue
    @Column(name = "show_id")
    private UUID id;

    @Column(name = "show_title", nullable = false)
    private String title;

    @Column(name = "show_description", nullable = false)
    private String description;

    @ElementCollection
    @CollectionTable(name = "showtimes", joinColumns = @JoinColumn(name = "show_id"))
    private List<Showtime> showtimes;
}