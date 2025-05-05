package com.hiberus.mapper;

import com.hiberus.dto.PromotionResponse;
import com.hiberus.model.Promotion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromotionResponseMapper {

    PromotionResponse toDto(Promotion promotion);
}