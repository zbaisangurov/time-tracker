package com.projects.timetracker.controller;

import com.projects.timetracker.dto.TaskRequest;
import com.projects.timetracker.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody TaskRequest taskRequest) {
        taskService.createTask(taskRequest);
        return ResponseEntity.ok("Задача создана");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        taskService.updateTask(id, taskRequest.getName());
        return ResponseEntity.ok("Задача изменена");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Задача удалена");
    }
}
