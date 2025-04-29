package com.hiberus.mapper;

import com.hiberus.dto.UpdateResponse;
import com.hiberus.model.ReservationStatus;

import java.util.List;
import java.util.UUID;

public class UpdateResponseMapper {

    public static UpdateResponse toDto(List<UUID> reservationIds, List<ReservationStatus> reservationStatus) {
        UpdateResponse dto = new UpdateResponse();
        dto.setReservationIds(reservationIds);
        dto.setReservationStatus(reservationStatus);
        return dto;
    }
}