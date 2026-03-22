package com.example.springlabb.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateEntityDTO(
        @NoEntry
        Long id,

        @NoEntry
        String title,

        @NoEntry
        String year,

        @Size(max = 250)
        String description,

        @NoEntry
        String director,

        @NoEntry
        String length

) {
}
