package com.project.first;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class SimpleTaskService implements TaskService {

	private static List<Task> tasks = new ArrayList<>();
	private final AtomicLong counter = new AtomicLong();
	private TaskData taskData = new TaskData();

	public TaskData getTaskData() {
		return taskData;
	}

	public void setTaskData(TaskData taskData) {
		this.taskData = taskData;
	}

	@Override
	public long produceTask(String description) {
		Task task = new Task();
		task.setDescription(description);
		task.setId(counter.incrementAndGet());
		tasks.add(task);
		return task.getId();
	}

	@Override
	public Task findTaskById(long id) {
		for (Task task : tasks) {
			if (task.getId() == id) {
				return task;
			}
		}
		return null;
	}

}
