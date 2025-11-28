package org.simplecash.projet_fall_ndeyefatou.repository;

import org.simplecash.projet_fall_ndeyefatou.entity.CompteEpargneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompteEpargneRepository extends JpaRepository<CompteEpargneEntity, Long> {

    Optional<CompteEpargneEntity> findByClientId(Long clientId);
}
