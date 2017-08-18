package com.project.first.taskdata;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.first.task.Task;
import com.project.first.task.TaskRepository;

public class DatabaseTaskDataLoader implements TaskDataLoader {

	@Autowired
	private TaskDataRepository repository;

	@Autowired
	private TaskRepository taskRepository;

	private Long lastId;

	@Override
	public void addTaskDataToTask(long id, TaskData taskData) {
		Task task = taskRepository.findById(id);
		if(task == null) {
			//TODO: which should be used and when
			// find 2 more interesting (the most common/popular) exceptions
//			IllegalStateException
//			NullPointerException
			throw new IllegalArgumentException(String.format("Task does not exist: %d", id));
		}
		
		TaskData data = repository.save(taskData);
		data.setTask(task);
		taskRepository.findById(id).setTaskData(data);
		repository.save(taskData);
		lastId = data.getId();

	}

	@Override
	public TaskData getLoadedTaskData() {
		return repository.findById(lastId);
	}

}
