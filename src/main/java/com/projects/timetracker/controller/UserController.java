package com.projects.timetracker.controller;

import com.projects.timetracker.dto.UserRequest;
import com.projects.timetracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest.getName());
        return ResponseEntity.ok("Пользователь создан");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestParam String name) {
        userService.updateUser(id, name);
        return ResponseEntity.ok("Имя пользователя изменено");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Пользователь с Id " + id + " удален");
    }
}
