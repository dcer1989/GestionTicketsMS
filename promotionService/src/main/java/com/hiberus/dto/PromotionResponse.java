package com.hiberus.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PromotionResponse(
    UUID promotionId, // ID único de la promoción
    String name, // Nombre de la promoción
    String description, // Descripción de la promoción
    float discountPercentage, // Porcentaje de descuento ofrecido
    String validFrom, // Fecha de inicio de la promoción
    String validUntil // Fecha de finalización de la promoción
) {}