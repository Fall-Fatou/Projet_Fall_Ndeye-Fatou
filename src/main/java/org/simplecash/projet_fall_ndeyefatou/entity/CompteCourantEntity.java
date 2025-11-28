package org.simplecash.projet_fall_ndeyefatou.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "compte_courant")
public class CompteCourantEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Double solde = 0.0;

    private Double decouvertAutorise = -1000.0;

    @OneToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    public CompteCourantEntity(ClientEntity client) {
        this.client = client;
    }
}
