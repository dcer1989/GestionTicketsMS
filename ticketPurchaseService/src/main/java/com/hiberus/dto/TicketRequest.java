package com.hiberus.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public record TicketRequest(
    @NotNull UUID reservationId, // ID de la reserva de asientos
    UUID promotionId, // ID de la promoción (opcional)
    @NotNull @Email String email, // Correo electrónico del cliente
    @NotNull PaymentDetails paymentDetails // Detalles del pago
) {}