package org.simplecash.projet_fall_ndeyefatou.service;

import lombok.RequiredArgsConstructor;
import org.simplecash.projet_fall_ndeyefatou.dto.OperationDto;
import org.simplecash.projet_fall_ndeyefatou.entity.CompteCourantEntity;
import org.simplecash.projet_fall_ndeyefatou.entity.CompteEpargneEntity;
import org.simplecash.projet_fall_ndeyefatou.entity.OperationEntity;
import org.simplecash.projet_fall_ndeyefatou.enums.OperationType;
import org.simplecash.projet_fall_ndeyefatou.mapper.OperationMapper;
import org.simplecash.projet_fall_ndeyefatou.repository.CompteCourantRepository;
import org.simplecash.projet_fall_ndeyefatou.repository.CompteEpargneRepository;
import org.simplecash.projet_fall_ndeyefatou.repository.OperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OperationServiceImpl implements OperationService {

    private final CompteCourantRepository compteCourantRepository;
    private final CompteEpargneRepository compteEpargneRepository;
    private final OperationRepository operationRepository;
    private final OperationMapper mapper;

    @Override
    public OperationDto depot(Long compteCourantId, Double montant) {

        if (montant == null || montant <= 0) {
            throw new RuntimeException("Le montant doit être supérieur à 0");
        }

        CompteCourantEntity cc = compteCourantRepository.findById(compteCourantId)
                .orElseThrow(() -> new RuntimeException("Compte courant introuvable"));

        cc.setSolde(cc.getSolde() + montant);
        compteCourantRepository.save(cc);

        OperationEntity op = new OperationEntity();
        op.setMontant(montant);
        op.setType(OperationType.DEPOT);
        op.setCompteDestination(cc);

        return mapper.toDto(operationRepository.save(op));
    }

    @Override
    public OperationDto transfertCCversCE(Long compteCourantId, Long compteEpargneId, Double montant) {

        if (montant == null || montant <= 0) {
            throw new RuntimeException("Le montant doit être supérieur à 0");
        }

        CompteCourantEntity cc = compteCourantRepository.findById(compteCourantId)
                .orElseThrow(() -> new RuntimeException("Compte courant introuvable"));

        CompteEpargneEntity ce = compteEpargneRepository.findById(compteEpargneId)
                .orElseThrow(() -> new RuntimeException("Compte épargne introuvable"));

        if (cc.getSolde() - montant < cc.getDecouvertAutorise()) {
            throw new RuntimeException("Solde insuffisant pour transfert CC → CE");
        }

        cc.setSolde(cc.getSolde() - montant);
        ce.setSolde(ce.getSolde() + montant);

        compteCourantRepository.save(cc);
        compteEpargneRepository.save(ce);

        OperationEntity op = new OperationEntity();
        op.setMontant(montant);
        op.setType(OperationType.TRANSFERT_CC_TO_CE);
        op.setCompteSource(cc);
        op.setEpargneDestination(ce);

        return mapper.toDto(operationRepository.save(op));
    }

    @Override
    public OperationDto transfertCEversCC(Long compteEpargneId, Long compteCourantId, Double montant) {

        if (montant == null || montant <= 0) {
            throw new RuntimeException("Le montant doit être supérieur à 0");
        }

        CompteEpargneEntity ce = compteEpargneRepository.findById(compteEpargneId)
                .orElseThrow(() -> new RuntimeException("Compte épargne introuvable"));

        CompteCourantEntity cc = compteCourantRepository.findById(compteCourantId)
                .orElseThrow(() -> new RuntimeException("Compte courant introuvable"));

        if (ce.getSolde() < montant) {
            throw new RuntimeException("Solde insuffisant sur le compte épargne");
        }

        ce.setSolde(ce.getSolde() - montant);
        cc.setSolde(cc.getSolde() + montant);

        compteEpargneRepository.save(ce);
        compteCourantRepository.save(cc);

        OperationEntity op = new OperationEntity();
        op.setMontant(montant);
        op.setType(OperationType.TRANSFERT_CE_TO_CC);
        op.setEpargneSource(ce);
        op.setCompteDestination(cc);

        return mapper.toDto(operationRepository.save(op));
    }

    @Override
    public OperationDto virement(Long sourceCompteId, Long destinationCompteId, Double montant) {

        if (montant == null || montant <= 0) {
            throw new RuntimeException("Le montant doit être supérieur à 0");
        }

        if (sourceCompteId.equals(destinationCompteId)) {
            throw new RuntimeException("Impossible d'effectuer un virement vers le même compte");
        }

        CompteCourantEntity source = compteCourantRepository.findById(sourceCompteId)
                .orElseThrow(() -> new RuntimeException("Compte source introuvable"));

        CompteCourantEntity dest = compteCourantRepository.findById(destinationCompteId)
                .orElseThrow(() -> new RuntimeException("Compte destination introuvable"));

        if (source.getSolde() - montant < source.getDecouvertAutorise()) {
            throw new RuntimeException("Solde insuffisant pour le virement");
        }

        source.setSolde(source.getSolde() - montant);
        dest.setSolde(dest.getSolde() + montant);

        compteCourantRepository.save(source);
        compteCourantRepository.save(dest);

        OperationEntity op = new OperationEntity();
        op.setMontant(montant);
        op.setType(OperationType.VIREMENT_CC);
        op.setCompteSource(source);
        op.setCompteDestination(dest);

        return mapper.toDto(operationRepository.save(op));
    }

    @Override
    public List<OperationDto> historiqueCompteCourant(Long compteCourantId) {

        // Vérifier que le compte existe
        compteCourantRepository.findById(compteCourantId)
                .orElseThrow(() -> new RuntimeException("Compte courant introuvable"));

        return operationRepository
                .findByCompteSource_IdOrCompteDestination_Id(compteCourantId, compteCourantId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<OperationDto> historiqueCompteEpargne(Long compteEpargneId) {

        compteEpargneRepository.findById(compteEpargneId)
                .orElseThrow(() -> new RuntimeException("Compte épargne introuvable"));

        return operationRepository
                .findByEpargneSource_IdOrEpargneDestination_Id(compteEpargneId, compteEpargneId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

}

