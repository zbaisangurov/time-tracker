package com.projects.timetracker.exception;

public class TimeNotFoundException extends RuntimeException {
    public TimeNotFoundException(String message) {
        super(message);
    }
}
