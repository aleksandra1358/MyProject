package com.project.first.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

	@Autowired
	TaskService service;

	@GetMapping("tasks")
	public List<Task> getTasks() {
		return null;
	}

	@GetMapping("tasks/{id}")
	public List<Task> getTasks(long id) {
		return null;
	}

	@PostMapping("tasks")
	public long produceTask(@RequestParam(value = "description", required = false) String description) {
		return service.produceTask(description);
	}

}
