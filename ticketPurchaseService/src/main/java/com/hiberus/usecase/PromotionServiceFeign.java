package com.hiberus.usecase;

import com.hiberus.client.PromotionServiceClient;
import com.hiberus.dto.ApplyPromotionRequest;
import com.hiberus.dto.ApplyPromotionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("feign-PromotionService")
@RequiredArgsConstructor
@Slf4j
public class PromotionServiceFeign {

    private final PromotionServiceClient promotionServiceClient;

    public ApplyPromotionResponse applyPromotion(ApplyPromotionRequest request) {

        log.info("Applying promotion for ticket: {} with promotionId: {}", request.ticketId(), request.promotionId());

        return promotionServiceClient.applyPromotion(request);
    }
}
