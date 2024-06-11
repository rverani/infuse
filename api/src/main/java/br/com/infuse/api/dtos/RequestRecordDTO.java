package br.com.infuse.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record RequestRecordDTO(@NotNull int idControl,
                               Date dtCadastro,
                               @NotBlank String name,
                               @NotNull BigDecimal value,
                               int quantity,
                               String idClient) {
}
