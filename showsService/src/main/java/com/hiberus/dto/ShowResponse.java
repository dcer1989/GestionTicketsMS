package com.hiberus.dto;

import java.util.UUID;

public record ShowResponse(
    UUID id,
    String title,
    String description
) {}