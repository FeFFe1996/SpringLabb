package com.example.springlabb.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record EntityDTO(
        @NotNull
        @NotEmpty
        Long id,

        @NotNull
        @NotEmpty
        String title,

        @NotNull
        @NotEmpty
        String year,

        @NotNull
        @NotEmpty
        String description,

        @NotNull
        @NotEmpty
        String director,

        @NotNull
        @NotEmpty
        String length
        ) {
}
