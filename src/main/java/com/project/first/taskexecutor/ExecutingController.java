package com.project.first.taskexecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutingController {

	@Autowired
	private TaskExecutor taskExecutor;

	@RequestMapping("/execute")
	public void executeTask(@RequestParam(value = "id") long id) {
		taskExecutor.executeTask(id);
	}
	
	@RequestMapping("/result")
	public double downloadResult(@RequestParam(value = "id") long id) {
		return taskExecutor.downloadResult(id);
	}

}
