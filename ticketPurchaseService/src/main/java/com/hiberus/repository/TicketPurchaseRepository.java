package com.hiberus.repository;

import com.hiberus.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketPurchaseRepository extends JpaRepository<Ticket, UUID> {

}