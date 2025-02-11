package com.projects.timetracker.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeReportResponse {
    private String interval;
    private String taskName;

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TimeReportResponse(String interval, String taskName) {
        this.interval = interval;
        this.taskName = taskName;
    }
    public TimeReportResponse() {}
}
