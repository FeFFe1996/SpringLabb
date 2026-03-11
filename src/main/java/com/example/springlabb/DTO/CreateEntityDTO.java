package com.example.springlabb.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateEntityDTO(
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
