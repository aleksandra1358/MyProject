package com.project.first;

import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseTaskDataLoader implements TaskDataLoader{

	@Autowired
	private TaskDataRepository repository;
	
	private Long lastId;
	
	@Override
	public void loadData(TaskData taskData) {
		TaskData data = repository.save(taskData);
		lastId = data.getId();
		
	}

	@Override
	public TaskData getLoadedTaskData() {
		return repository.findById(lastId);
	}

}
