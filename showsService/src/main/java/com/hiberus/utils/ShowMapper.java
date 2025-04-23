package com.hiberus.utils;

import com.hiberus.dto.ShowDto;
import com.hiberus.models.Show;

import java.util.stream.Collectors;

public class ShowMapper {

    public static ShowDto toDto(Show show) {
        if (show == null) {
            return null;
        }
        return ShowDto.builder()
                .id(show.getId())
                .title(show.getTitle())
                .description(show.getDescription())
                .showtimes(show.getShowtimes().stream()
                        .map(ShowtimeMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}