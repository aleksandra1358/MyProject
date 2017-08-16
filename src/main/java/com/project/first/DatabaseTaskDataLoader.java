package com.project.first;

import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseTaskDataLoader implements TaskDataLoader {

	@Autowired
	private TaskDataRepository repository;

	@Autowired
	private TaskRepository taskRepository;

	private Long lastId;

	@Override
	public void loadData(long id, TaskData taskData) {
		TaskData data = repository.save(taskData);
		data.setTask(taskRepository.findById(id));
		lastId = data.getId();

	}

	@Override
	public TaskData getLoadedTaskData() {
		return repository.findById(lastId);
	}

}
