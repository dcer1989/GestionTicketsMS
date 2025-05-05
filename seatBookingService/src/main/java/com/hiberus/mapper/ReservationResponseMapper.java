package com.hiberus.mapper;

import com.hiberus.dto.ReservationResponse;
import com.hiberus.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hiberus.usecase.SeatDetailsFormatterService;

@Mapper(componentModel = "spring", uses = {SeatDetailsFormatterService.class})
public interface ReservationResponseMapper {

    @Mapping(source = "id", target = "reservationId")
    @Mapping(source = "seatIds", target = "seats", qualifiedByName = "formatSeats")
    ReservationResponse toDto(Reservation reservation);
}