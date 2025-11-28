package org.simplecash.projet_fall_ndeyefatou.dto;

public record ClientUpdateDto(
        String name,
        String surname,
        String address,
        String telephone,
        String postal_code,
        String city
) {
}
