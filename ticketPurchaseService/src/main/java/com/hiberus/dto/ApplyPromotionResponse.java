package com.hiberus.dto;

import java.util.UUID;

public record ApplyPromotionResponse(
        UUID ticketId, // ID del ticket al que se aplicó la promoción
        double finalPrice // Precio final del ticket después de aplicar la promoción
) {}