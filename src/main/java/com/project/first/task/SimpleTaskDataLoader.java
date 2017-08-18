package com.project.first.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.first.taskdata.TaskData;
import com.project.first.taskdata.TaskDataLoader;

public class SimpleTaskDataLoader implements TaskDataLoader {

	private List<TaskData> taskDataList;

	@Autowired
	private TaskService taskService;

	@PostConstruct
	public void init() {
		taskDataList = new ArrayList<>();
	}

	@Override
	public void addTaskDataToTask(long id, TaskData taskData) {
		TaskData data = taskData;
		data.setTask(taskService.findTaskById(id));
		taskService.findTaskById(id).setTaskData(data);
		taskDataList.add(data);

	}

	@Override
	public TaskData getLoadedTaskData() {
		return taskDataList.get(taskDataList.size() - 1);
	}

}
