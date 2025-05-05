package com.hiberus.dto;

import java.util.UUID;

public record SeatResponse(
    UUID id,
    String row,
    int number,
    SeatStatusDto status,
    boolean isActive
) {}