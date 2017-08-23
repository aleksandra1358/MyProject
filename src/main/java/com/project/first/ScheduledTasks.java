package com.project.first;

import com.project.first.taskexecutor.ExecutingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks
{
    private final ExecutingController controller;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    public ScheduledTasks(ExecutingController controller) {this.controller = controller;}

    @Async
    @Scheduled(fixedRate = 50000)
    public void executeAllTasksAsync()
    {
        log.info("Execution!");
        controller.executeAll();
    }
}
