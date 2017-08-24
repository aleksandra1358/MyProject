package com.project.first;

import com.project.first.task.TaskRepository;
import com.project.first.taskexecutor.TaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@ConfigurationProperties(prefix = "schedule")
public class ScheduledTasks
{
    private static boolean isWorking = false;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private final TaskRepository repository;
    private final TaskExecutor taskExecutor;

    @Autowired
    public ScheduledTasks(TaskRepository repository, TaskExecutor taskExecutor)
    {
        this.repository = repository;
        this.taskExecutor = taskExecutor;
    }

    @Scheduled(fixedRateString = "${schedule.executeTasks}")
    public void schedule()
    {
        if(!isWorking)
        {
            log.info("Start!");
            Collection<Future<String>> results = new ArrayList<>();
            isWorking = true;
            repository.findAll().forEach(task -> results.add(taskExecutor.executeTask(task.getId())));
            results.forEach(result -> {
                try
                {
                    result.get();
                }
                catch(InterruptedException | ExecutionException e)
                {
                    e.printStackTrace();
                }
            });
            log.info("Stop!");
            isWorking = false;
        }
    }
}
