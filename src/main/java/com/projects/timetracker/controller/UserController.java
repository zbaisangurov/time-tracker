package com.projects.timetracker.controller;

import com.projects.timetracker.dto.UserRequest;
import com.projects.timetracker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Управление пользователями")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @Operation(summary = "Создание", description = "Создает нового пользователя с указанным именем")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest.getName());
        return ResponseEntity.ok("Пользователь создан");
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Изменение", description = "Изменяет имя пользователя")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        userService.updateUser(id, userRequest.getName());
        return ResponseEntity.ok("Имя пользователя изменено");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление", description = "Удаляет пользователя с указанным id")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Пользователь с Id " + id + " удален");
    }
}
