package com.hiberus.dto;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public record UpdateRequest(
    @NotNull UUID reservationId
) {}