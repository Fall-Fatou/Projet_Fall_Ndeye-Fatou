package org.simplecash.projet_fall_ndeyefatou.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "compte_epargne")
public class CompteEpargneEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Double solde = 0.0;

    private Double taux = 0.03;

    @OneToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    public CompteEpargneEntity(ClientEntity client) {
        this.client = client;
    }
}
