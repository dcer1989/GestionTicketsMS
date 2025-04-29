package com.hiberus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SeatResponse {
    private UUID id;
    private String row;
    private int number;
    private SeatStatus status;
    private boolean isActive;
}