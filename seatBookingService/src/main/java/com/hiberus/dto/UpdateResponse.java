package com.hiberus.dto;

import com.hiberus.model.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateResponse {
    private List<UUID> reservationIds;
    private List<ReservationStatus> reservationStatus;
}