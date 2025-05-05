package com.hiberus.mapper;

import com.hiberus.dto.SeatStatusDto;
import com.hiberus.model.SeatStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SeatStatusMapper {

    @Named("toDto")
    String toDto(SeatStatus seatStatus);

    SeatStatus toEntity(SeatStatusDto seatStatusDto);
}