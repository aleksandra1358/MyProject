package com.project.first.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseTaskService implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public Task produceTask() {
		return produceTask("Test");
	}

	@Override
	public Task produceTask(String description) {
		Task task = new Task();
		task.setDescription(description);
		return taskRepository.save(task);
	}

	@Override
	public Task findTaskById(long id) {
		if (id < 1) {
			throw new IllegalArgumentException("Id must be greater than zero");
		}

		return taskRepository.findById(id);
	}

	@Override
	public List<Task> downloadExistingTasks() {
		return taskRepository.findAll();
	}

}
