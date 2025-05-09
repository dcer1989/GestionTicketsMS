package com.hiberus.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ticket_promotions")
@Getter
@Setter
public class TicketPromotion {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID ticketId; // ID del ticket

    @ManyToOne
    @JoinColumn(name = "promotion_id", nullable = false)
    private Promotion promotion; // Promoción asociada
}
