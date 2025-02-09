package com.projects.timetracker.service;

import com.projects.timetracker.entity.Task;
import com.projects.timetracker.entity.Time;
import com.projects.timetracker.exception.TaskNotFoundException;
import com.projects.timetracker.exception.TimeAlreadyExistsException;
import com.projects.timetracker.exception.TimeNotFoundException;
import com.projects.timetracker.exception.UserNotFoundException;
import com.projects.timetracker.repository.TaskRepository;
import com.projects.timetracker.repository.TimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeService {
    private final TimeRepository timeRepository;
    private final TaskRepository taskRepository;
    public TimeService(TimeRepository timeRepository, TaskRepository taskRepository) {
        this.timeRepository = timeRepository;
        this.taskRepository = taskRepository;
    }
    private final Logger log = LoggerFactory.getLogger(TimeService.class);

    @Transactional
    public void startTracking(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Такой задачи не найдено"));
        if (timeRepository.existsByTaskIdAndStopPointIsNull(taskId)) {
            throw new TimeAlreadyExistsException("Отслеживание задачи уже начато");
        }
        Time time = new Time();
        time.setTask(task);
        time.setStartPoint(LocalDateTime.now());
        timeRepository.save(time);
        log.info("Трекинг задачи {} начат", taskId);
    }

    @Transactional
    public void stopTracking(Long taskId) {
        Time time = timeRepository
                .findFirstByTaskIdAndStopPointIsNullOrderByStartPointDesc(taskId)
                .orElseThrow(() -> new TimeNotFoundException("Отслеживание задачи еще не начиналось"));
        time.setStopPoint(LocalDateTime.now());
        timeRepository.save(time);
        log.info("Трекинг задачи {} остановлен", taskId);
    }

    @Transactional
    public void deleteUserTracking(Long userId) {
        if (!taskRepository.existsByUserId(userId)) {
            throw new UserNotFoundException("Такой пользователь не найден");
        }
        List<Task> userTasks = taskRepository.findByUserId(userId);
        if (userTasks.isEmpty()) {
            throw new TimeNotFoundException("Трекинг пользователя не найден");
        }
        List<Long> taskIds = userTasks.stream().map(Task::getId).toList();
        timeRepository.deleteByTaskIdIn(taskIds);
        log.info("Удалены все записи трекинга пользователя {}", userId);
    }

}
