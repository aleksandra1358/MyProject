package com.project.first;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.project.first.task.TaskRepository;
import com.project.first.taskexecutor.TaskExecutor;

@Service
@ConfigurationProperties(prefix = "schedule")
public class ScheduledTasks {

	private volatile boolean isWorking = false;

	private static final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);

	private final TaskRepository repository;
	private final TaskExecutor taskExecutor;

	@Autowired
	public ScheduledTasks(TaskRepository repository, TaskExecutor taskExecutor) {
		this.repository = repository;
		this.taskExecutor = taskExecutor;
	}

	@Scheduled(fixedRateString = "${schedule.executeTasks}")
	public void schedule() {
		synchronized (this) {
			if (isWorking) {
				return;
			}
			isWorking = true;
			LOG.info("Start!");
		}

		executeNewTasks();
		LOG.info("Stop!");
		synchronized (this) {
			isWorking = false;
		}
	}

	private void executeNewTasks() {
		Collection<Future<String>> results = new ArrayList<>();
		repository.findAll().forEach(task -> results.add(taskExecutor.executeTask(task.getId())));
		results.forEach(result -> {
			try {
				result.get();
			} catch (InterruptedException | ExecutionException e) {
				LOG.error("an error: {}", e.getMessage(), e);
			}
		});
	}
}
