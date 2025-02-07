package com.projects.timetracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "times")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startPoint;

    @Column
    private LocalDateTime stopPoint;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

}
