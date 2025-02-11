package com.projects.timetracker.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "times")

public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_point", nullable = false)
    private LocalDateTime startPoint;

    @Column (name = "stop_point")
    private LocalDateTime stopPoint;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(LocalDateTime startPoint) {
        this.startPoint = startPoint;
    }

    public LocalDateTime getStopPoint() {
        return stopPoint;
    }

    public void setStopPoint(LocalDateTime stopPoint) {
        this.stopPoint = stopPoint;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Time(Long id, LocalDateTime startPoint, LocalDateTime stopPoint, Task task) {
        this.id = id;
        this.startPoint = startPoint;
        this.stopPoint = stopPoint;
        this.task = task;
    }

    public Time() {}
}
