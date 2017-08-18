package com.project.first.taskdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskDataController {

	@Autowired
	TaskDataLoader taskDataLoader;

	@GetMapping("taskdata/{taskId}")
	public TaskData getTaskData(long id) {
		return null;
	}

	@PutMapping("taskdata/{taskId}")
	public void addTaskData(@PathVariable("taskId") long id, @RequestBody TaskData taskData) {
		taskDataLoader.addTaskDataToTask(id, taskData);
	}

}
