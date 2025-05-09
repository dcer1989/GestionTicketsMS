package com.hiberus.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "promotions")
@Getter
@Setter
public class Promotion {

    @Id
    private UUID promotionId; // ID único de la promoción

    @Column(nullable = false)
    private String name; // Nombre de la promoción

    private String description; // Descripción de la promoción

    @Column(nullable = false)
    private float discountPercentage; // Porcentaje de descuento ofrecido

    private Instant validFrom; // Fecha de inicio de la promoción
    private Instant validUntil; // Fecha de finalización de la promoción
}