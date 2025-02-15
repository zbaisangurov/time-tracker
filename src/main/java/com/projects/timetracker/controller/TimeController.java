package com.projects.timetracker.controller;

import com.projects.timetracker.service.TimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/times")
@Tag(name = "Трекинг", description = "Управление трекингом задач")
public class TimeController {
    private final TimeService timeService;
    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping("/start/{id}")
    @Operation(summary = "Старт", description = "Начинает трекинг задачи по её id")
    public ResponseEntity<String> startTracking(@PathVariable Long id) {
        timeService.startTracking(id);
        return ResponseEntity.ok("Трекинг задачи " + id + " начат");
    }

    @PutMapping("/stop/{id}")
    @Operation(summary = "Стоп", description = "Останавливает трекинг задачи по её id")
    public ResponseEntity<String> stopTracking(@PathVariable Long id) {
        timeService.stopTracking(id);
        return ResponseEntity.ok("Трекинг задачи " + id + " остановлен");
    }

    @DeleteMapping("/user/{userId}")
    @Operation(summary = "Удаление", description = "Удаляет весь трекинг пользователя по его id")
    public ResponseEntity<String> deleteUserTracking(@PathVariable Long userId) {
        timeService.deleteUserTracking(userId);
        return ResponseEntity.ok("Все трекинги пользователя " + userId + " удалены");
    }
}
