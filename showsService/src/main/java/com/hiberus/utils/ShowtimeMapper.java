package com.hiberus.utils;

import com.hiberus.dto.ShowtimeDto;
import com.hiberus.models.Showtime;

public class ShowtimeMapper {

    public static ShowtimeDto toDto(Showtime showtime) {
        if (showtime == null) {
            return null;
        }
        return ShowtimeDto.builder()
                .id(showtime.getId())
                .room(showtime.getRoom())
                .time(showtime.getTime())
                .build();
    }
}