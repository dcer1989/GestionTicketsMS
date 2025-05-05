package com.hiberus.usecase;

import com.hiberus.mapper.PromotionResponseMapper;
import com.hiberus.model.Promotion;
import com.hiberus.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllPromotionsUseCase {

    private final PromotionRepository promotionRepository;
    private final PromotionResponseMapper promotionResponseMapper;

    public List<Promotion> getAllPromotions() {

        log.info("Use case to get all promotions");

        return promotionRepository.findAll();
    }
}