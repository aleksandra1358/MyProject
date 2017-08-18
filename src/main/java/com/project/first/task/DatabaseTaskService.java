package com.project.first.task;

import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseTaskService implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public long produceTask(String description) {
		Task task = new Task();
		task.setDescription(description);
		return taskRepository.save(task).getId();
	}

	@Override
	public Task findTaskById(long id) {
		return taskRepository.findById(id);
	}

}
