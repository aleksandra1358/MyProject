package com.project.first.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class DatabaseTaskService implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public long produceTask(String description) {
		Task task = new Task();
		task.setDescription(description);
		return taskRepository.save(task).getId();
	}

	public Task produceTask() {
		Task task = new Task();
		task.setDescription("Test");
		return taskRepository.save(task);
	}

	@Override
	public Task findTaskById(long id) {
		if (id > 0) {
			return taskRepository.findById(id);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public List<Task> downloadExistingTasks() {
		return taskRepository.findAll();
	}

}
