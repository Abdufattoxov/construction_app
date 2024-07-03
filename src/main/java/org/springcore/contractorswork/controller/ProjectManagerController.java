package org.springcore.contractorswork.controller;

import lombok.RequiredArgsConstructor;
import org.springcore.contractorswork.entity.ProjectManager;
import org.springcore.contractorswork.entity.Task;
import org.springcore.contractorswork.service.ProjectManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pm")
public class ProjectManagerController {
    private final ProjectManagerService projectManagerService;

    @GetMapping("/{pmId}/tasks")
    public String allTasks(@PathVariable Long pmId, Model model){
        List<Task> tasks = projectManagerService.findAlTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("pmId", pmId);
        return "redirect:/pm/" + pmId + "/tasks";
    }

    @PostMapping("{pmId}/takeTask/{taskId}")
    public String takeATask(@PathVariable Long pmId, @PathVariable Long taskId) {
        ProjectManager projectManager = projectManagerService.takeExistTask(taskId, pmId);
        return "redirect:/pm/" + pmId + "/tasks";
    }
                // logout a message "Task is not done, look at the priority"
    @PostMapping("/{pmId}/taskDone")
    public String putDone(@RequestParam Long taskId, @PathVariable Long pmId, Model model) {
        projectManagerService.putTaskDone(taskId);
        projectManagerService.isAllTasksFinished(pmId);
        return "redirect:/pm/" + pmId;
    }

    @GetMapping("/{pmId}")
    public String getPM(@PathVariable Long pmId, Model model) {
        ProjectManager pm = projectManagerService.findPM(pmId);
        model.addAttribute("projectManager", pm);
        return "pm/pmDetail";
    }
}


/*
package org.springcore.contractorswork.controller;

import lombok.RequiredArgsConstructor;
import org.springcore.contractorswork.entity.ProjectManager;
import org.springcore.contractorswork.entity.Task;
import org.springcore.contractorswork.service.ProjectManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/pm")
public class ProjectManagerController {
    private final ProjectManagerService projectManagerService;

    @PutMapping("{pmId}/takeTask/{taskId}")
    public  ResponseEntity<ProjectManager> takeATask(@PathVariable Long pmId, @PathVariable Long taskId){
        return ResponseEntity.ok( projectManagerService.takeExistTask(taskId, pmId));
    }

    @GetMapping("/{pmId}/allTasks")
    public ResponseEntity<List<Task>> allTasks(@PathVariable Long pmId){
        return ResponseEntity.ok(projectManagerService.findAll());
    }

    @GetMapping("/{pmId}")
    public ResponseEntity<ProjectManager> getPM(@PathVariable Long pmId ){
        return ResponseEntity.ok(projectManagerService.findPM(pmId));
    }

    @PostMapping("/{pmId}/task_done")
    public ResponseEntity putDone(@RequestParam Long taskId, @PathVariable Long pmId){
        projectManagerService.isAllTasksFinished(pmId);
        return ResponseEntity.ok(projectManagerService.putTaskDone(taskId));
    }


}
*/
