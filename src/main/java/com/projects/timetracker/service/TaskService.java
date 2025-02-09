package com.projects.timetracker.service;

import com.projects.timetracker.dto.TaskRequest;
import com.projects.timetracker.entity.Task;
import com.projects.timetracker.entity.User;
import com.projects.timetracker.exception.TaskNotFoundException;
import com.projects.timetracker.exception.UserNotFoundException;
import com.projects.timetracker.repository.TaskRepository;
import com.projects.timetracker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }
    private final static Logger log = LoggerFactory.getLogger(TaskService.class);

    @Transactional
    public void createTask(TaskRequest taskRequest) {
        User user = userRepository.findById(taskRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Такой пользователь не найден"));
        Task task = new Task();
        task.setName(taskRequest.getName());
        task.setUser(user);
        log.info("Создание новой задачи для пользователя " + task.getUser().getName() + ": " + task.getName());
        taskRepository.save(task);
    }

    @Transactional
    public void updateTask (Long id, String newName) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException("Задача с Id " + id + " не найдена"));
        task.setName(newName);
        log.info("Обновляются данные о задаче " + id);
        taskRepository.save(task);
    }

    @Transactional
    public void deleteTask (Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Задача с Id " + id + " не найдена");
        }
        taskRepository.deleteById(id);
        log.info("Удаление задачи с Id {}", id);
    }

}
