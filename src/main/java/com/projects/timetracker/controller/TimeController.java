package com.projects.timetracker.controller;

import com.projects.timetracker.service.TimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/times")
public class TimeController {
    private final TimeService timeService;
    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping("/start/{id}")
    public ResponseEntity<String> startTracking(@PathVariable Long id) {
        timeService.startTracking(id);
        return ResponseEntity.ok("Трекинг задачи " + id + " начат");
    }

    @PutMapping("/stop/{id}")
    public ResponseEntity<String> stopTracking(@PathVariable Long id) {
        timeService.stopTracking(id);
        return ResponseEntity.ok("Трекинг задачи " + id + " остановлен");
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUserTracking(@PathVariable Long userId) {
        timeService.deleteUserTracking(userId);
        return ResponseEntity.ok("Все трекинги пользователя " + userId + " удалены");
    }
}
