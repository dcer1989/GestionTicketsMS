package com.hiberus.dto;

import com.hiberus.model.ReservationStatus;
import java.util.List;
import java.util.UUID;

public record UpdateResponse(
    UUID reservationId,
    ReservationStatus reservationStatus
) {}