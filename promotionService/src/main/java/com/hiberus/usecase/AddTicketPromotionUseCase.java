package com.hiberus.usecase;

import com.hiberus.exception.PromotionNotFoundException;
import com.hiberus.model.Promotion;
import com.hiberus.model.TicketPromotion;
import com.hiberus.repository.PromotionRepository;
import com.hiberus.repository.TicketPromotionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddTicketPromotionUseCase {

    private final TicketPromotionRepository ticketPromotionRepository;
    private final PromotionRepository promotionRepository;

    public void addTicketPromotion(UUID ticketId, UUID promotionId) {

        log.info("Registering promotion with ID: {} applied to ticket with ID: {}", promotionId, ticketId);

        // Verificar que la promoción existe
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new PromotionNotFoundException(promotionId));

        // Crear la entidad TicketPromotion
        TicketPromotion ticketPromotion = new TicketPromotion();
        ticketPromotion.setId(UUID.randomUUID());
        ticketPromotion.setTicketId(ticketId);
        ticketPromotion.setPromotion(promotion);

        ticketPromotionRepository.save(ticketPromotion);
    }
}
