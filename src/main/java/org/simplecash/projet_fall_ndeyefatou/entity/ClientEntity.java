package org.simplecash.projet_fall_ndeyefatou.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Client name should not be empty!")
    private String name;

    @NotBlank(message = "Client surname should not be empty!")
    private String surname;

    @NotBlank(message = "Client address should not be empty!")
    private String address;

    @NotBlank(message = "Client tel should not be empty!")
    private String telephone;

    @NotBlank(message = "Client postal_code should not be empty!")
    private String postal_code;

    @NotBlank(message = "Client city should not be empty!")
    private String city;

    @ManyToOne
    @JoinColumn(name = "conseiller_id")
    private ConseillerEntity conseiller;

    public ClientEntity(String name, String surname, String address, String telephone, String postal_code, String city) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telephone = telephone;
        this.postal_code = postal_code;
        this.city = city;
    }
}
