package com.hiberus.mapper;

import com.hiberus.dto.ApplyPromotionResponse;
import com.hiberus.model.TicketPromotion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ApplyPromotionResponseMapper {

    ApplyPromotionResponse toDto(UUID ticketId, double finalPrice);
}