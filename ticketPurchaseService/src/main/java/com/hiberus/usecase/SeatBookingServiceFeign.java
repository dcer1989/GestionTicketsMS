package com.hiberus.usecase;

import com.hiberus.client.SeatBookingServiceClient;
import com.hiberus.dto.UpdateRequest;
import com.hiberus.dto.UpdateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("feign-SeatBookingService")
@RequiredArgsConstructor
@Slf4j
public class SeatBookingServiceFeign {

    private final SeatBookingServiceClient seatBookingServiceClient;

    public UpdateResponse updateReservationStatus(UpdateRequest updateRequest) {

        log.info("Actualizando el estado de la reserva: {}", updateRequest.reservationId());

        return seatBookingServiceClient.updateReservations(updateRequest);
    }
}