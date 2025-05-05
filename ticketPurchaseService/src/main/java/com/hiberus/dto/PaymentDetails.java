package com.hiberus.dto;

import javax.validation.constraints.NotNull;

public record PaymentDetails(
    @NotNull String cardNumber, // Número de la tarjeta
    @NotNull String cardHolderName, // Nombre del titular de la tarjeta
    @NotNull String expirationDate, // Fecha de expiración (MM/YY)
    @NotNull String cvv // Código CVV de la tarjeta
) {}