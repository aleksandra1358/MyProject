package com.project.first.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import com.project.first.taskdata.TaskData;

public class SimpleTaskService implements TaskService {

	private List<Task> tasks;
	private AtomicLong counter;
	private TaskData taskData;

	@PostConstruct
	public void init() {
		tasks = new ArrayList<>();
		counter = new AtomicLong();
		taskData = new TaskData();
	}

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
		throw new IllegalArgumentException("There is no task with given id");
	}

	@Override
	public List<Task> downloadExistingTasks() {
		return tasks;
	}

    @Override
    public Task produceTask()
    {
        return null;
    }

}
