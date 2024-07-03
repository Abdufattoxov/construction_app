package org.springcore.contractorswork.repository;

import org.springcore.contractorswork.entity.ProjectManager;
import org.springcore.contractorswork.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllById(Optional<ProjectManager> byId);
}
