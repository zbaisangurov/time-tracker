package com.projects.timetracker.dto;

import java.time.LocalDateTime;

public class TotalTimeReportResponse {
    private String formattedTotalTime;

    public String getFormattedTotalTime() {
        return formattedTotalTime;
    }

    public void setFormattedTotalTime(String formattedTotalTime) {
        this.formattedTotalTime = formattedTotalTime;
    }

    public TotalTimeReportResponse(String formattedTotalTime) {
        this.formattedTotalTime = formattedTotalTime;
    }
}
