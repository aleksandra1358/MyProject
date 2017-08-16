package com.project.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadingController {

	@Autowired
	TaskDataLoader taskDataLoader;

	@RequestMapping("/loading")
	public void loadTaskData(@RequestBody TaskData taskData) {
		taskDataLoader.loadData(taskData);
	}

}
