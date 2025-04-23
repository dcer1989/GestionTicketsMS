package com.hiberus.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "seats")
@Getter
@Setter
public class Seat {

    @Id
    private UUID id;
    private String row;
    private int number;
    private SeatStatus status;
    private boolean isActive;
}