package com.project.first;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.first.task.TaskService;
import com.project.first.taskdata.TaskData;
import com.project.first.taskdata.TaskDataLoader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskDataLoaderTest {

	@Autowired
	private TaskDataLoader taskDataLoader;

	@Autowired
	private TaskService taskService;

	@Test
	public void loadData_sameOperation() {
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			numbers.add(i);
		}
		TaskData taskData = new TaskData();
		taskData.setNumbers(numbers);
		Operation operation = Operation.ADD;
		taskData.setOperation(operation);
		long id = taskService.produceTask("aaa");
		taskDataLoader.addTaskDataToTask(id, taskData);
		assertEquals(operation, taskDataLoader.getTaskData(id).getOperation());
	}

	@Test
	public void loadData_sameNumbers() {
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			numbers.add(i);
		}
		TaskData taskData = new TaskData();
		taskData.setNumbers(numbers);
		Operation operation = Operation.ADD;
		taskData.setOperation(operation);
		long id = taskService.produceTask("aaa");
		taskDataLoader.addTaskDataToTask(id, taskData);
		ArrayList<Integer> loadedNumbers = new ArrayList<>(taskDataLoader.getTaskData(id).getNumbers());
		for (int i = 0; i < 5; i++) {
			if (numbers.get(i) != loadedNumbers.get(i)) {
				fail("Numbers are different");
				// assertEquals(expected, actual);
				// TODO: fail vs assert
			}
		}
	}

}
