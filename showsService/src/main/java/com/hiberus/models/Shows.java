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
public class Shows {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    @CollectionTable(name = "showtimes", joinColumns = @JoinColumn(name = "show_id"))
    private List<Showtime> showtimes;
}