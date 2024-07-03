package org.springcore.contractorswork.service;

import lombok.RequiredArgsConstructor;
import org.springcore.contractorswork.entity.ProjectManager;
import org.springcore.contractorswork.entity.Task;
import org.springcore.contractorswork.repository.ProjectManagerRepository;
import org.springcore.contractorswork.repository.SubContractorRepository;
import org.springcore.contractorswork.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectManagerService {
    private final ProjectManagerRepository projectManagerRepository;
    private final TaskRepository taskRepository;
    private final SubContractorRepository subContractorRepository;

    public ProjectManager createPM(ProjectManager projectManager, Long scID) {
        projectManager.setSubContractor(subContractorRepository.findById(scID).get());
        return projectManagerRepository.save(projectManager);
    }

    public boolean isAllTasksFinished(Long id) {
        Optional<ProjectManager> optionalPM = projectManagerRepository.findById(id);

        if (!optionalPM.isPresent()) {
            throw new IllegalArgumentException("ProjectManager not found");
        }

        ProjectManager pm = optionalPM.get();
        List<Task> tasks = pm.getTasks();

        long counter = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isDone()) {
                counter++;
            }
        }
        if (counter == tasks.size()) {
            pm.setDone(true);
            ProjectManager savedPM = projectManagerRepository.save(pm);
            return savedPM.isDone();
        }
        return pm.isDone();
    }


    public ProjectManager takeExistTask(Long taskId, Long pmID) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            Optional<ProjectManager> pmOpt = projectManagerRepository.findById(pmID);

            if (pmOpt.isPresent()) {
                ProjectManager projectManager = pmOpt.get();
                task.setProjectManager(projectManager);
                taskRepository.save(task);

                List<Task> tasks = projectManager.getTasks();
                tasks.add(task);
                projectManager.setTasks(tasks);

                ProjectManager savedProjectManager = projectManagerRepository.save(projectManager);
                return savedProjectManager;
            } else {
                throw new RuntimeException("Project Manager not found");
            }
        } else {
            throw new RuntimeException("Task not found");
        }
    }

    public ResponseEntity putTaskDone(Long taskId) {
        Task task = taskRepository.findById(taskId).get();
        List<Task> tasks = taskRepository.findAll();
        Integer taskPriority = task.getPriority();
        if (taskPriority <= 1) {
            task.setDone(true);
        } else {
            boolean isTrue = false;
            for (Task t : tasks) {
                if (t.getPriority() == taskPriority - 1 && t.isDone()) {
                    task.setDone(true);
                    isTrue = true;
                    break;
                }
            }
            if (isTrue) {
                System.out.println("Everything is works fine with task done field");
            } else {
                return new ResponseEntity("Look at the priority of the tasks", HttpStatus.BAD_REQUEST);
            }
        }
        Task saveTask = taskRepository.save(task);
        return new ResponseEntity(task, HttpStatus.OK);
    }


    public List<Task> findAlTasks() {
        List<Task> allTasks = taskRepository.findAll();
        List<Task> tasksWithoutManager = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.getProjectManager() == null) {
                tasksWithoutManager.add(task);
            }
        }
        return tasksWithoutManager;
    }

    public ProjectManager findPM(Long pmId) {
        return projectManagerRepository.findById(pmId).get();
    }

    public List<ProjectManager> getAllPms(Long scId) {
        List<ProjectManager> allPMs = projectManagerRepository.findAll();
        List<ProjectManager> allForReturn = new ArrayList<>();
        for (ProjectManager pm : allPMs){
            if(pm.getSubContractor().getId() == scId){
                allForReturn.add(pm);
            }
        }

        return allForReturn;
    }

}
