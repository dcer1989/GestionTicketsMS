package com.hiberus.mapper;

import com.hiberus.dto.ReseervationByIdResponse;
import com.hiberus.model.ReservationStatus;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ReservationByIdMapper {

    ReseervationByIdResponse toDto(UUID reservationId, ReservationStatus reservationStatus);
}