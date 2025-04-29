package com.hiberus.dto;

import javax.validation.constraints.NotBlank;

public record ShowRequest(
    @NotBlank(message = "Title is required")
    String title,

    String description
) {
}
