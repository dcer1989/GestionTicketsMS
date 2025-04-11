package com.hiberus.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowsDto {
    private UUID id;
    private String title;
    private String description;
    private List<ShowtimeDto> showtimes;
}
