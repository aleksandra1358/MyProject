package com.project.first.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

	@Autowired
	TaskService service;

	@GetMapping("tasks")
	public ResponseEntity<List<Task>> getTasks() {
		return new ResponseEntity<List<Task>>(service.downloadExistingTasks(), HttpStatus.OK);
	}

	@GetMapping("tasks/{id}")
	public ResponseEntity<Task> getTask(@PathVariable("id") long id) {
		return new ResponseEntity<Task>(service.findTaskById(id), HttpStatus.OK);
	}

	@PostMapping("tasks")
	public long produceTask(@RequestParam(value = "description", required = false) String description) {
		return service.produceTask(description);
	}

}
