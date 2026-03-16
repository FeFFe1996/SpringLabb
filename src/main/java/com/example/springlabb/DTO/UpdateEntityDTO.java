package com.example.springlabb.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateEntityDTO(
        String title,

        String year,

        @Size(max = 150)
        String description,


        String director,

        String length

) {
}
