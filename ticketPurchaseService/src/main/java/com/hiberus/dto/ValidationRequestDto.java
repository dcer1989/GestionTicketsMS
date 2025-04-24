package com.hiberus.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ValidationRequestDto {
    @NotNull
    private List<UUID> seatIds;
}