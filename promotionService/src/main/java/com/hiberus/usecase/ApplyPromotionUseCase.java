package com.hiberus.usecase;

import com.hiberus.exception.PromotionNotFoundException;
import com.hiberus.model.Promotion;
import com.hiberus.model.TicketPromotion;
import com.hiberus.repository.PromotionRepository;
import com.hiberus.repository.TicketPromotionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplyPromotionUseCase {

    private final PromotionRepository promotionRepository;
    private final AddTicketPromotionUseCase addTicketPromotionUseCase;

    public double applyPromotion(UUID promotionId, UUID ticketId, double ticketPrice) {

        log.info("Applying promotion with ID: {} to ticket with ID: {}", promotionId, ticketId);

        if (promotionId == null) {

            log.warn("No promotion applied as promotionId is null. Returning original ticket price: {}", ticketPrice);

            return ticketPrice;
        }

        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new PromotionNotFoundException(promotionId));

        Instant now = Instant.now();
        if (now.isBefore(promotion.getValidFrom()) || now.isAfter(promotion.getValidUntil())) {

            log.warn("Promotion with ID: {} is not valid at this time. Returning original ticket price: {}", promotionId, ticketPrice);

            return ticketPrice;
        }

        double discount = ticketPrice * (promotion.getDiscountPercentage() / 100);
        double finalPrice = ticketPrice - discount;

        addTicketPromotionUseCase.addTicketPromotion(ticketId, promotionId);

        log.info("Promotion applied successfully to ticket: {}. Final price: {}", ticketId, finalPrice);

        return finalPrice;
    }
}