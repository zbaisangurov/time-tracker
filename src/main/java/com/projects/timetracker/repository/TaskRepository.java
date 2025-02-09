package com.projects.timetracker.repository;

import com.projects.timetracker.entity.Task;
import com.projects.timetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    boolean existsByName(String name);
}
