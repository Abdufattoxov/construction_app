package org.springcore.contractorswork.repository;

import org.springcore.contractorswork.entity.SubContractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubContractorRepository extends JpaRepository<SubContractor, Long> {
}
