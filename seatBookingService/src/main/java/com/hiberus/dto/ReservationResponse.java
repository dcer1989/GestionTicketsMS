package com.hiberus.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ReservationResponse(
    UUID reservationId,
    List<String> seats,
    String reservedBy,
    Instant reservationExpiresAt,
    String status
) {}