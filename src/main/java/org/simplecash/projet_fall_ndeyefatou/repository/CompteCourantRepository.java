package org.simplecash.projet_fall_ndeyefatou.repository;

import org.simplecash.projet_fall_ndeyefatou.entity.CompteCourantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompteCourantRepository extends JpaRepository<CompteCourantEntity, Long> {

    Optional<CompteCourantEntity> findByClientId(Long clientId);
}
