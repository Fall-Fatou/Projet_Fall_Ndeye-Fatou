package org.simplecash.projet_fall_ndeyefatou.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "conseillers")
public class ConseillerEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Conseiller name should not be empty!")
    private String name;

    @NotBlank(message = "Conseiller email should not be empty!")
    private String email;

    private String telephone;

    @OneToMany(mappedBy = "conseiller")
    private java.util.List<ClientEntity> clients = new ArrayList<>();



    public ConseillerEntity(String name, String email, String telephone) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }
}
