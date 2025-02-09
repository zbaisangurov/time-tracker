package com.projects.timetracker.exception;

public class TimeAlreadyExistsException extends RuntimeException {
    public TimeAlreadyExistsException(String message) {
        super(message);
    }
}
