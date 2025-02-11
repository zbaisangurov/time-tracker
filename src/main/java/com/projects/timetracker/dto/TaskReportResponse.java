package com.projects.timetracker.dto;

public class TaskReportResponse {
    private String taskName;
    private String workTime;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public TaskReportResponse(String taskName, String workTime) {
        this.taskName = taskName;
        this.workTime = workTime;
    }
}
