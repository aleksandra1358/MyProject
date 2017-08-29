package com.project.first;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.first.task.TaskRepository;
import com.project.first.task.TaskService;
import com.project.first.taskdata.TaskData;
import com.project.first.taskdata.TaskDataLoader;

@RestController
public class GenerationController {
	private final TaskService service;
	private final TaskDataLoader taskDataLoader;
	private final TaskRepository repository;

	@Autowired
	public GenerationController(TaskService service, TaskDataLoader taskDataLoader, TaskRepository repository) {
		this.service = service;
		this.taskDataLoader = taskDataLoader;
		this.repository = repository;
	}

	@PostMapping("tasksGenerator")
	public void generateTasks(@RequestParam(value = "amount") int amount, @RequestBody TaskData taskData) {
		IntStream.range(0, amount)
				.forEach(n -> taskDataLoader.addTaskDataToTask(service.produceTask().getId(), taskData));
	}

	@DeleteMapping("drop")
	public void deleteAllTasks() {
		repository.deleteAll();
	}
}
