package com.hiberus.repository;

import com.hiberus.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketPurchaseServiceRepository extends JpaRepository<Ticket, UUID> {

}