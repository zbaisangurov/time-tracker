package com.projects.timetracker.repository;

import com.projects.timetracker.entity.Task;
import com.projects.timetracker.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
    List<Time> findByTask(Task task);
    List<Time> findByStartPointBetween(LocalDateTime startPoint, LocalDateTime stopPoint);
    boolean existsByTaskIdAndStopPointIsNull(Long taskId);
    Optional<Time> findFirstByTaskIdAndStopPointIsNullOrderByStartPointDesc(Long taskId);
    void deleteByTaskIdIn(List<Long> taskIds);

    Optional<Time> findByTaskIdAndStopPointIsNull(Long taskId);
}
