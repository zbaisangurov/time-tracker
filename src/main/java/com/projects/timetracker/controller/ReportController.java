package com.projects.timetracker.controller;

import com.projects.timetracker.dto.ReportRequest;
import com.projects.timetracker.dto.TaskReportResponse;
import com.projects.timetracker.dto.TimeReportResponse;
import com.projects.timetracker.dto.TotalTimeReportResponse;
import com.projects.timetracker.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskReportResponse>> getTaskReports(@RequestBody ReportRequest reportRequest) {
        return ResponseEntity.ok(reportService.getTaskReport(reportRequest));
    }

    @GetMapping("/intervals")
    public ResponseEntity<List<TimeReportResponse>> getIntervalReports(@RequestBody ReportRequest reportRequest) {
        return ResponseEntity.ok(reportService.getTimeReport(reportRequest));
    }

    @GetMapping("/summary")
    public ResponseEntity<TotalTimeReportResponse> getSummaryReport(@RequestBody ReportRequest reportRequest) {
        return ResponseEntity.ok(reportService.getSummaryReport(reportRequest));
    }
}
