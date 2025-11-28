package org.simplecash.projet_fall_ndeyefatou.repository;

import org.simplecash.projet_fall_ndeyefatou.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity, Long> {

    List<OperationEntity> findByCompteSource_IdOrCompteDestination_Id(Long sourceId, Long destId);

    List<OperationEntity> findByEpargneSource_IdOrEpargneDestination_Id(Long sourceId, Long destId);
}


