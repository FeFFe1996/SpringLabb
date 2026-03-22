package com.example.springlabb.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateEntityDTO(
        @NoEntry
        @Size(min=3, max=150)
        String title,

        @NoEntry
        @Size(min=4, max = 4)
        String year,

        @Size(max = 250)
        String description,

        @NoEntry
        @Size(min=5, max=150)
        String director,

        @NotNull
        @NotEmpty
        String length
) {
}
