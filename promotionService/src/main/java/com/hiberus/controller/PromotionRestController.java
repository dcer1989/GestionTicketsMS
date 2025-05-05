package com.hiberus.controller;

import com.hiberus.dto.ApplyPromotionRequest;
import com.hiberus.dto.ApplyPromotionResponse;
import com.hiberus.dto.PromotionResponse;
import com.hiberus.mapper.ApplyPromotionResponseMapper;
import com.hiberus.mapper.PromotionResponseMapper;
import com.hiberus.usecase.GetAllPromotionsUseCase;
import com.hiberus.usecase.ApplyPromotionUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PromotionRestController {

    private final GetAllPromotionsUseCase getAllPromotionsUseCase;
    private final ApplyPromotionUseCase applyPromotionUseCase;
    private final PromotionResponseMapper promotionResponseMapper;
    private final ApplyPromotionResponseMapper applyPromotionResponseMapper;

    @GetMapping("/v1/promotions")
    @ResponseStatus(HttpStatus.OK)
    public List<PromotionResponse> getAllPromotions() {

        log.info("Request received to fetch all promotions");

        return getAllPromotionsUseCase.getAllPromotions().stream()
                .map(promotionResponseMapper::toDto)
                .toList();    }

    @PostMapping("/v1/promotions/apply")
    @ResponseStatus(HttpStatus.OK)
    public ApplyPromotionResponse applyPromotion(@RequestBody ApplyPromotionRequest request) {

        log.info("Request received to apply promotion: {} to ticket: {}", request.promotionId(), request.ticketId());

        return applyPromotionResponseMapper.toDto(
                request.ticketId(),
                applyPromotionUseCase.applyPromotion(request.promotionId(), request.ticketId(), request.ticketPrice()));
    }
}