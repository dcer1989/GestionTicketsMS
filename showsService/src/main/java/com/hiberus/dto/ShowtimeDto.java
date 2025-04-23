package com.hiberus.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeDto {
    private Integer id;
    private String room;
    private String time;
}
