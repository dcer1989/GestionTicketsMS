package com.hiberus.dto;

import com.hiberus.model.ReservationStatus;

import java.util.UUID;

public record ReseervationByIdResponse(
    UUID reservationId,
    ReservationStatus reservationStatus
) {}