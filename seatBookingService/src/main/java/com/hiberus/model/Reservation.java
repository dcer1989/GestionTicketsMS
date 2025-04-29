package com.hiberus.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document(collection = "reservations")
@Getter
@Setter
public class Reservation {

    @Id
    private UUID id; // ID único de la reserva
    private List<UUID> seatIds; // IDs de los asientos reservados
    private UUID showId; // ID del show asociado
    private Integer showtimeId; // ID del horario del show
    private String reservedBy; // Usuario que realizó la reserva
    private LocalDateTime reservationExpiresAt; // Fecha y hora de expiración de la reserva
    private ReservationStatus status = ReservationStatus.ACTIVE; // Estado de la reserva
}