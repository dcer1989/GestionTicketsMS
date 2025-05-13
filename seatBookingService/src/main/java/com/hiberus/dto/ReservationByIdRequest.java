package com.hiberus.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ReservationByIdRequest(
    @NotNull UUID reservationId
) {}