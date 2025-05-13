package com.hiberus.client;

import com.hiberus.dto.ReservationByIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@FeignClient(name = "seatBookingService")
public interface SeatBookingServiceClient {

    @GetMapping("/v1/reservations/{reservationId}")
    @ResponseStatus(HttpStatus.OK)
    ReservationByIdResponse getReservationById(@PathVariable UUID reservationId);
}