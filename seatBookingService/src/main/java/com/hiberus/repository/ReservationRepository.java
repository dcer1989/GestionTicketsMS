package com.hiberus.repository;

import com.hiberus.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, UUID> {
    List<Reservation> findBySeatIdsIn(List<UUID> seatIds);
}