package com.hiberus.usecase;

import com.hiberus.client.SeatBookingServiceClient;
import com.hiberus.dto.ReservationByIdResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("feign-SeatBookingService")
@RequiredArgsConstructor
@Slf4j
public class SeatBookingServiceFeign {

    private final SeatBookingServiceClient seatBookingServiceClient;

    public ReservationByIdResponse getReservationById(UUID reservationId) {

        log.info("Checking reservation status: {}", reservationId);

        return seatBookingServiceClient.getReservationById(reservationId);
    }
}