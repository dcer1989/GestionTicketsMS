package com.hiberus.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PaymentDetailsDto {

    @NotNull
    private String cardNumber; // Número de la tarjeta

    @NotNull
    private String cardHolderName; // Nombre del titular de la tarjeta

    @NotNull
    private String expirationDate; // Fecha de expiración (MM/YY)

    @NotNull
    private String cvv; // Código CVV de la tarjeta
}