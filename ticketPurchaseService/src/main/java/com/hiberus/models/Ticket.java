package com.hiberus.models;

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
    @GeneratedValue
    @Column(name = "ticket_id")
    private UUID id;

    @Column(name = "reservation_id", nullable = false)
    private UUID reservationId;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "status", nullable = false)
    private TicketStatus status;
}