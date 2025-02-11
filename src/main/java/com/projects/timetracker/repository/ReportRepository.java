package com.projects.timetracker.repository;

import com.projects.timetracker.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Time, Long> {
    List<Time> findByTaskUserIdAndStartPointGreaterThanEqualAndStopPointLessThanEqual(Long userId, LocalDateTime periodFrom, LocalDateTime periodTo);
    List<Time> findByStartPointGreaterThanEqualAndStopPointLessThanEqual(LocalDateTime periodFrom, LocalDateTime periodTo);
    List<Time> findAllByTaskUserIdAndStartPointAfterAndStopPointBefore(Long userId, LocalDateTime periodFrom, LocalDateTime periodTo);
}
