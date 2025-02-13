package com.projects.timetracker.service;

import com.projects.timetracker.entity.Time;
import com.projects.timetracker.repository.TimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TimeStopper {
    private TimeRepository timeRepository;
    public TimeStopper(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }
    private Logger log = LoggerFactory.getLogger(TimeStopper.class);

    @Scheduled(cron = "0 59 23 * * *")
    public void stopTimes () {
        log.info("Закрываем все открытые задачи");
        List<Time> times = timeRepository.findAllByStopPointIsNull();
        times.stream().forEach(time -> time.setStopPoint(LocalDateTime.now()));
        timeRepository.saveAll(times);
    }
}
