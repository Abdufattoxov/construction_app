package org.springcore.contractorswork.service;

import lombok.RequiredArgsConstructor;
import org.springcore.contractorswork.entity.Task;
import org.springcore.contractorswork.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
