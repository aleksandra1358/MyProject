package com.project.first;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadingController {

	@Autowired
	TaskDataLoader taskDataLoader;

	@RequestMapping("/{taskId}/data")
	public void loadTaskData(@PathVariable("taskId") long id, @RequestBody TaskData taskData) {
		taskDataLoader.loadData(id,taskData);
	}

}
