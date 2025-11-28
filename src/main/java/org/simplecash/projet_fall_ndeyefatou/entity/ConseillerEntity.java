package org.simplecash.projet_fall_ndeyefatou.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
public class ConseillerEntity {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Conseiller name should not be empty!")
    private String name;
    @NotBlank(message = "Conseiller email should not be empty!")
    private String email;
    private String telephone;

    public ConseillerEntity(String name, String email, String telephone) {
        this.name = name;
        this.email = email;
        this.telephone = telephone;
    }
}
