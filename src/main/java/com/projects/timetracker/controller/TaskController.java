package com.projects.timetracker.controller;

import com.projects.timetracker.dto.TaskRequest;
import com.projects.timetracker.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Задачи", description = "Управление задачами")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/create")
    @Operation(summary = "Создание", description = "Создает новую задачу для пользователя")
    public ResponseEntity<String> createTask(@RequestBody TaskRequest taskRequest) {
        taskService.createTask(taskRequest);
        return ResponseEntity.ok("Задача создана");
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Изменение", description = "Изменяет задачу по её id")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        taskService.updateTask(id, taskRequest.getName());
        return ResponseEntity.ok("Задача изменена");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление", description = "Удаляет задачу по её id")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Задача удалена");
    }
}
