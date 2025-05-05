package com.hiberus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Getter
@Setter
public class Ticket {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID reservationId;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private TicketStatus status;
}