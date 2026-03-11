package com.example.springlabb.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateEntityDTO(
        @NoEntry
        String title,

        @NoEntry
        String year,

        @NoEntry
        String description,

        @Size(max = 150)
        String director,

        @NotNull
        @NotEmpty
        String length
) {
}
