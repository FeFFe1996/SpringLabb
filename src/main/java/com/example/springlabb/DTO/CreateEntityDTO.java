package com.example.springlabb.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateEntityDTO(
        @NoEntry
        String title,

        @NoEntry
        String year,

        @Size(max = 250)
        String description,

        @NoEntry
        String director,

        @NotNull
        @NotEmpty
        String length
) {
}
