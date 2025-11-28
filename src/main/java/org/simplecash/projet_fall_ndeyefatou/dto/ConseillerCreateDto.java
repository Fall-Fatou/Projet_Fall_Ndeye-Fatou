package org.simplecash.projet_fall_ndeyefatou.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ConseillerCreateDto(
        @NotBlank(message = "Conseiller name should not be empty!")
        String name,

        @Email
        @NotBlank(message = "Conseiller email should not be empty!")
        String email,

        String telephone
        )
{
}
