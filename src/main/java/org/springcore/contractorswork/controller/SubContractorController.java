package org.springcore.contractorswork.controller;

import lombok.RequiredArgsConstructor;
import org.springcore.contractorswork.entity.ProjectManager;
import org.springcore.contractorswork.entity.SubContractor;
import org.springcore.contractorswork.entity.Task;
import org.springcore.contractorswork.service.ProjectManagerService;
import org.springcore.contractorswork.service.SubContractorService;
import org.springcore.contractorswork.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/subContractor")
public class SubContractorController {

    private final SubContractorService subContractorService;
    private final ProjectManagerService projectManagerService;
    private final TaskService taskService;

    @GetMapping("/{scId}")
    public String subContractorPage(@PathVariable Long scId, Model model) {
        model.addAttribute("subContractor", subContractorService.findById(scId));
        model.addAttribute("newTask", new Task());
        model.addAttribute("newProjectManager", new ProjectManager());
        model.addAttribute("tasks", taskService.findAll());
        return "sub_contractor";
    }

    @PostMapping("/{scId}/createTask")
    public String createTask(@PathVariable Long scId, Task task) {
        SubContractor subContractor = subContractorService.findById(scId);
        taskService.createTask(task);
        return "redirect:/subContractor/" + scId;
    }

    @PostMapping("/{scId}/createPM")
    public String createProjectManager(@PathVariable Long scId, ProjectManager projectManager) {
        projectManagerService.createPM(projectManager, scId);
        return "redirect:/subContractor/" + scId;
    }



    @GetMapping("/{scId}/info")
    public String infoAboutSubContractor(@PathVariable Long scId, Model model) {
        SubContractor subContractor = subContractorService.findById(scId);
        boolean isDone = subContractor.getProjectManagers().stream()
                .allMatch(ProjectManager::isDone);

        model.addAttribute("subContractor", subContractor);
        model.addAttribute("isDone", isDone);
        return "sub_contracotr_info";
    }

    @PostMapping("/{scId}/done_pm/{pmId}")
    public String putDoneToPM(@PathVariable Long scId, @PathVariable Long pmId){
        ProjectManager pm = subContractorService.findPM(pmId);
        subContractorService.putDone(pm);
        return "redirect:/api/subContractor/" + scId + "/pms" ;
    }


}



/*import lombok.RequiredArgsConstructor;
import org.springcore.contractorswork.entity.ProjectManager;
import org.springcore.contractorswork.entity.SubContractor;
import org.springcore.contractorswork.entity.Task;
import org.springcore.contractorswork.service.ProjectManagerService;
import org.springcore.contractorswork.service.SubContractorService;
import org.springcore.contractorswork.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/subContractor")

public class SubContractorController {
    private final SubContractorService subContractorService;
    private final ProjectManagerService projectManagerService;
    private final TaskService taskService;

    @PostMapping("/{scID}/create/PM")
    public ResponseEntity<ProjectManager> createPM(@RequestBody ProjectManager projectManager, @PathVariable Long scID){
        return ResponseEntity.ok(projectManagerService.createPM(projectManager, scID));
    }

    @PostMapping("/create/Task")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.createTask(task));
    }
}*/
