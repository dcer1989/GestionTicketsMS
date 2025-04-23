package com.hiberus.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TicketRequestDto {

    @NotNull
    private List<UUID> seatIds; // Lista de IDs de los asientos reservados

    @NotNull
    @Email
    private String email; // Correo electrónico del cliente

    @NotNull
    private PaymentDetailsDto paymentDetails; // Detalles del pago
}