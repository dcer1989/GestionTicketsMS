package com.hiberus.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "shows")
@Setter
@Getter
public class Show {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

}