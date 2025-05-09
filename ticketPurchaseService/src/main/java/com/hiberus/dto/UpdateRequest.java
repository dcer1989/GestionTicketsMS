package com.hiberus.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record UpdateRequest(
    @NotNull UUID reservationId
) {}