package com.projects.timetracker.service;

import com.projects.timetracker.dto.ReportRequest;
import com.projects.timetracker.dto.TaskReportResponse;
import com.projects.timetracker.dto.TimeReportResponse;
import com.projects.timetracker.dto.TotalTimeReportResponse;
import com.projects.timetracker.entity.Time;
import com.projects.timetracker.repository.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    private Logger log = LoggerFactory.getLogger(ReportService.class);

    public List<TaskReportResponse> getTaskReport(ReportRequest request) {
        log.info("Обрабатывается запрос на создание отчёта по всем задачам пользователя");
        List<Time> tasks = reportRepository.findByTaskUserIdAndStartPointGreaterThanEqualAndStopPointLessThanEqual(
                request.getUserId(), request.getPeriodFrom(), request.getPeriodTo()
        );
        return tasks.stream()
                .map(task -> new TaskReportResponse(
                        task.getTask().getName(),
                        formatDuration(Duration.between(task.getStartPoint(), task.getStopPoint()))))
                .sorted(Comparator.comparing(TaskReportResponse::getWorkTime).reversed())
                .toList();
    }

    public List<TimeReportResponse> getTimeReport(ReportRequest request) {
        log.info("Обрабатывается запрос на создание отчёта по всем временным интервалам");
        List<Time> times = reportRepository.findByStartPointGreaterThanEqualAndStopPointLessThanEqual(
                request.getPeriodFrom(), request.getPeriodTo()
        );
        return times.stream()
                .map(time -> new TimeReportResponse(
                        formatInterval(time.getStartPoint(), time.getStopPoint()),
                        time.getTask().getName()
                ))
                .toList();
    }

    public TotalTimeReportResponse getSummaryReport(ReportRequest request) {
        log.info("Обрабатывается запрос на создание отчёта по сумме трудозатрат");
        List<Time> summary = reportRepository.findAllByTaskUserIdAndStartPointAfterAndStopPointBefore(
                request.getUserId(), request.getPeriodFrom(), request.getPeriodTo()
        );
        Duration totalTime = summary.stream()
                .map(time -> Duration.between(time.getStartPoint(), time.getStopPoint()))
                .reduce(Duration.ZERO, Duration::plus);
        return new TotalTimeReportResponse(formatDuration(totalTime));
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        return String.format("%02d:%02d", hours, minutes);
    }
    private String formatInterval(LocalDateTime start, LocalDateTime stop) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return start.format(formatter) + " " + formatDuration(Duration.between(start, stop));
    }
}
