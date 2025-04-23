package com.hiberus.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReservationResult {
    private Reservation reservation;
    private List<Seat> seats;
}