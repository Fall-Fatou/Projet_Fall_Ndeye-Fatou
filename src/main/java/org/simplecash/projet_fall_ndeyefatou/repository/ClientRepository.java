package org.simplecash.projet_fall_ndeyefatou.repository;

import org.simplecash.projet_fall_ndeyefatou.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    int countByConseillerId(Long conseillerId);
}
