package com.project.first.taskdata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.first.Operation;

@RestController
public class TaskDataController {

	@Autowired
	TaskDataLoader taskDataLoader;

	@PutMapping("taskdata/{taskId}")
	public void addTaskData(@PathVariable("taskId") long id, @RequestBody TaskData taskData) {
		taskDataLoader.addTaskDataToTask(id, taskData);
	}

	@GetMapping("taskdata")
	public ResponseEntity<TaskData> getTaskData() {
		TaskData taskData = new TaskData();
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		taskData.setNumbers(numbers);
		taskData.setOperation(Operation.ADD);
		return new ResponseEntity<TaskData>(taskData, HttpStatus.OK);
	}

}
