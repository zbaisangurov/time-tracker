package com.projects.timetracker;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimeTrackerApplication {
  static {
    Dotenv dotenv = Dotenv.load();
    dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
  }

  public static void main(String[] args) {
    SpringApplication.run(TimeTrackerApplication.class, args);
  }

}
