package com.hiberus.mapper;

import com.hiberus.dto.ShowtimeResponse;
import com.hiberus.model.Showtime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {

    ShowtimeResponse toDto(Showtime showtime);
}