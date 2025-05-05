package com.hiberus.mapper;

import com.hiberus.dto.UpdateResponse;
import com.hiberus.model.ReservationStatus;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UpdateResponseMapper {

    UpdateResponse toDto(UUID reservationId, ReservationStatus reservationStatus);
}