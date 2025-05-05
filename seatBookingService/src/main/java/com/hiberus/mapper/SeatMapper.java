package com.hiberus.mapper;

import com.hiberus.dto.SeatResponse;
import com.hiberus.model.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = SeatStatusMapper.class)
public interface SeatMapper {

    @Mapping(source = "status", target = "status", qualifiedByName = "toDto")
    SeatResponse toDto(Seat seat);
}