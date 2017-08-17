package com.project.first;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

public class SimpleTaskDataLoader implements TaskDataLoader {

	private final ArrayList<TaskData> taskDataList = new ArrayList<>();
	
	@Autowired
	private TaskService taskService;

	@Override
	public void loadData(long id, TaskData taskData) {
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
