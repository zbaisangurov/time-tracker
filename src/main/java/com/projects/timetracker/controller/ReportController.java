package com.projects.timetracker.controller;

import com.projects.timetracker.dto.ReportRequest;
import com.projects.timetracker.dto.TaskReportResponse;
import com.projects.timetracker.dto.TimeReportResponse;
import com.projects.timetracker.dto.TotalTimeReportResponse;
import com.projects.timetracker.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
@Tag(name = "Отчёты", description = "Генерация отчётов по трудозатратам пользователей")
public class ReportController {
    private final ReportService reportService;
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/tasks")
    @Operation(summary = "Отчёт по задачам",
            description = "Показывает все трудозатраты пользователя по задачам за указанный период")
    public ResponseEntity<List<TaskReportResponse>> getTaskReports(@RequestBody ReportRequest reportRequest) {
        return ResponseEntity.ok(reportService.getTaskReport(reportRequest));
    }

    @GetMapping("/intervals")
    @Operation(summary = "Отчёт по интервалам",
            description = "Показывает все временные интервалы занятые работой за указанный период")
    public ResponseEntity<List<TimeReportResponse>> getIntervalReports(@RequestBody ReportRequest reportRequest) {
        return ResponseEntity.ok(reportService.getTimeReport(reportRequest));
    }

    @GetMapping("/summary")
    @Operation(summary = "Отчёт по всей работе",
            description = "Показывает сумму трудозатрат по всем задачам пользователя за указанный период")
    public ResponseEntity<TotalTimeReportResponse> getSummaryReport(@RequestBody ReportRequest reportRequest) {
        return ResponseEntity.ok(reportService.getSummaryReport(reportRequest));
    }
}
