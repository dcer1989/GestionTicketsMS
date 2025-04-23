package com.hiberus.repository;

import com.hiberus.models.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, UUID> {
    Optional<Reservation> findBySeatIdsContaining(UUID seatId);
}