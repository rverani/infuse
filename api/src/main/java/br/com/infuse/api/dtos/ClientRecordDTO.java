package br.com.infuse.api.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClientRecordDTO(@NotBlank String name,
                              @NotBlank String addressClient) {
}

