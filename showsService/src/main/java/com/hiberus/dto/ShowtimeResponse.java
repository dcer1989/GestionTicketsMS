package com.hiberus.dto;

import java.util.UUID;

public record ShowtimeResponse(
    UUID id,
    String room,
    String time
) {
}