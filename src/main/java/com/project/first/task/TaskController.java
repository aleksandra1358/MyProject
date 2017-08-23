package com.project.first.task;

import com.project.first.taskdata.TaskData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("tasksGenerator")
    public void generateTasks(@RequestParam(value = "amount") int amount) {
        for(int i = 0; i < amount; i++)
        {
            service.produceTask();
        }
    }

    @PutMapping("taskdata/{taskId}")
    public void addTaskData(@PathVariable("taskId") long id, @RequestBody TaskData taskData) {
        taskDataLoader.addTaskDataToTask(id, taskData);
    }
}
