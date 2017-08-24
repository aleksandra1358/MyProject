package com.project.first;

import com.project.first.task.TaskRepository;
import com.project.first.taskexecutor.TaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasks
{
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private final TaskRepository repository;
    private final TaskExecutor taskExecutor;

    @Autowired
    public ScheduledTasks(TaskRepository repository, TaskExecutor taskExecutor)
    {
        this.repository = repository;
        this.taskExecutor = taskExecutor;
    }

    @Scheduled(fixedRate = 10000)
    public void schedule()
    {
        log.info("Execution!");
        repository.findAll().forEach(task -> taskExecutor.executeTask(task.getId()));
    }
}
