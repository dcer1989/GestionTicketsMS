package com.hiberus.repository;

import com.hiberus.models.Seat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SeatsServiceRepository extends MongoRepository<Seat, UUID> {

}