package com.hiberus.repository;

import com.hiberus.model.TicketPromotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TicketPromotionRepository extends JpaRepository<TicketPromotion, UUID> {

    List<TicketPromotion> findByTicketId(UUID ticketId);

    List<TicketPromotion> findByPromotion_PromotionId(UUID promotionId);
}
