package com.hiberus.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "payments")
@Getter
@Setter
public class Payment {

    @Id
    private UUID paymentId;
    private UUID ticketId;
    private double finalPrice;
    private String paymentMethod;
    private Instant paymentTimestamp;
    private PaymentStatus status;
}
