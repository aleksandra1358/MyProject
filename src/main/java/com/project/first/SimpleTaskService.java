package com.project.first;

import java.util.ArrayList;

public class SimpleTaskService implements TaskService {

	private final ArrayList<Task> tasks = new ArrayList<>();

	@Override
	public long produceTask(String description) {
		Task task = new Task();
		task.setDescription(description);
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
