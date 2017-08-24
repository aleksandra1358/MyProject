package com.project.first.taskdata;

import com.project.first.task.Task;
import com.project.first.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseTaskDataLoader implements TaskDataLoader {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public void addTaskDataToTask(long id, TaskData taskData) {
		Task task = taskRepository.findById(id);
		if(task == null) {
			throw new IllegalArgumentException(String.format("Task does not exist: %d", id));
		}

		task.setTaskData(taskData);
		taskData.setTask(task);
		taskRepository.save(task);
	}

	@Override
	public TaskData getTaskData(long id) {
		return taskRepository.findById(id).getTaskData();
	}

}
