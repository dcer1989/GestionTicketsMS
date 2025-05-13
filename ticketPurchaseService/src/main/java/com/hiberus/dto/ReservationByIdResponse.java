package com.hiberus.dto;

import com.hiberus.model.ReservationStatus;

import java.util.UUID;

public record ReservationByIdResponse(
    UUID reservationId,
    ReservationStatus reservationStatus
) {}