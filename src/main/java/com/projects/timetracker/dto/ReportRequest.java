package com.projects.timetracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReportRequest {
    private Long userId;
    private LocalDateTime periodFrom;
    private LocalDateTime periodTo;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(LocalDateTime periodFrom) {
        this.periodFrom = periodFrom;
    }

    public LocalDateTime getPeriodTo() {
        return periodTo;
    }

    public void setPeriodTo(LocalDateTime periodTo) {
        this.periodTo = periodTo;
    }

    public ReportRequest() {
    }
    public ReportRequest(Long userId, LocalDateTime periodFrom, LocalDateTime periodTo) {
        this.userId = userId;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
    }
}
