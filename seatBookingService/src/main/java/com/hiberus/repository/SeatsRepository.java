package com.hiberus.repository;

import com.hiberus.model.Seat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SeatsRepository extends MongoRepository<Seat, UUID> {

}