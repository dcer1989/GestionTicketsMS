package com.hiberus.mapper;

import com.hiberus.dto.ReservationRequest;
import com.hiberus.model.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationRequestMapper {

    Reservation toEntity(ReservationRequest dto);

    ReservationRequest toDto(Reservation entity);
}