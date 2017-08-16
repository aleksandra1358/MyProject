package com.project.first;

import java.util.ArrayList;

public class SimpleTaskDataLoader implements TaskDataLoader {

	private final ArrayList<TaskData> taskDataList = new ArrayList<>();

	@Override
	public void loadData(TaskData taskData) {
		taskDataList.add(taskData);

	}

	@Override
	public TaskData getLoadedTaskData() {
		return taskDataList.get(taskDataList.size() - 1);
	}

}
