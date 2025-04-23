package com.hiberus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SeatResponseDto {
    private UUID id;
    private String row;
    private int number;
    private SeatStatusDto status;
    private boolean isActive;
}