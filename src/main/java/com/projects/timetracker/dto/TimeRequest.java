package com.projects.timetracker.dto;

import java.time.LocalDateTime;

public class TimeRequest {
    private LocalDateTime startPoint;
    private LocalDateTime stopPoint;
    private Long taskId;

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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
