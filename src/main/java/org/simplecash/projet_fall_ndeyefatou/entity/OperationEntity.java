package org.simplecash.projet_fall_ndeyefatou.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.simplecash.projet_fall_ndeyefatou.enums.OperationType;

import java.time.LocalDateTime;

@Entity
@Table(name = "operations")
@Getter
@Setter
@NoArgsConstructor
public class OperationEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Double montant;

    private LocalDateTime date = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private OperationType type;

    @ManyToOne
    @JoinColumn(name = "compte_source_id")
    private CompteCourantEntity compteSource;

    @ManyToOne
    @JoinColumn(name = "compte_destination_id")
    private CompteCourantEntity compteDestination;

    @ManyToOne
    @JoinColumn(name = "epargne_source_id")
    private CompteEpargneEntity epargneSource;

    @ManyToOne
    @JoinColumn(name = "epargne_destination_id")
    private CompteEpargneEntity epargneDestination;
}

