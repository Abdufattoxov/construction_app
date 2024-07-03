package org.springcore.contractorswork.repository;

import org.springcore.contractorswork.entity.GenContractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenContractorRepository extends JpaRepository<GenContractor, Long> {
    Optional<GenContractor> findUserByName(String username);
}
