package com.hiberus.dto;

import java.util.UUID;

public record ApplyPromotionRequest(
    UUID ticketId, // ID único del ticket
    UUID promotionId, // ID único de la promoción
    double ticketPrice // Precio del ticket
) {}