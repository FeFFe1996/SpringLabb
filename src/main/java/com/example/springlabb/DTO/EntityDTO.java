package com.example.springlabb.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EntityDTO(
        @NoEntry
        Long id,

        @NoEntry
        String title,

        @NoEntry
        String year,

        @NoEntry
        @Size(max=150)
        String description,

        @NoEntry
        String director,

        @NoEntry
        String length
        ) {
}
