package org.springcore.contractorswork.repository;

import org.springcore.contractorswork.entity.ProjectManager;
import org.springcore.contractorswork.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectManagerRepository extends JpaRepository<ProjectManager, Long> {

}
