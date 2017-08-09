package com.project.first;

import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseTaskService implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public long produceTask(String description) {
		Task task = new Task();
		task.setDescription(description);
		Task task2 = taskRepository.save(task);
		return task2.getId();
	}

	@Override
	public Task findTaskById(long id) {
		return taskRepository.findById(id);
	}

}
