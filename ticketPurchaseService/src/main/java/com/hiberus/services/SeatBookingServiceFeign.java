package com.hiberus.services;

import com.hiberus.dto.ValidationRequestDto;
import com.hiberus.dto.ValidationResponseDto;

public interface SeatBookingServiceFeign {

    /**
     * Valida los asientos proporcionados.
     *
     * @param request Objeto que contiene los IDs de los asientos a validar.
     * @return Respuesta con el resultado de la validación.
     */
    ValidationResponseDto validateSeats(ValidationRequestDto request);
}