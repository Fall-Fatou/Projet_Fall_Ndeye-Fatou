package org.simplecash.projet_fall_ndeyefatou.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientCreateDto(
        @NotBlank(message = "Client surname should not be empty!")
        String name,

        @NotBlank(message = "Client surname should not be empty!")
        String surname,

        @NotBlank(message = "Client address should not be empty!")
        String address,

        @NotBlank(message = "Client tel should not be empty!")
        String telephone,

        @NotBlank(message = "Client postal_code should not be empty!")
        String postal_code,

        @NotBlank(message = "Client city should not be empty!")
        String city
) {

}
