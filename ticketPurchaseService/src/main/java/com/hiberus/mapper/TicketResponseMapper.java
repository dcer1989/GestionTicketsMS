package com.hiberus.mapper;

import com.hiberus.dto.TicketResponse;
import com.hiberus.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketResponseMapper {

    @Mapping(target = "finalPrice", source = "price")
    TicketResponse toDto(Ticket ticket);
}