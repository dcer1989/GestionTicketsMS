package com.hiberus.usecase;

import com.hiberus.exception.ReservationNotFoundException;
import com.hiberus.exception.SeatNotFoundException;
import com.hiberus.model.Reservation;
import com.hiberus.model.Seat;
import com.hiberus.repository.ReservationRepository;
import com.hiberus.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetReservationByIdUseCase {

    private final ReservationRepository reservationRepository;

    public Reservation getReservationById(UUID reservationId) {

        log.info("Getting reservation with ID: {}", reservationId);

        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));
    }
}
