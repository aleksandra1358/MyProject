package com.project.first;

import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskDataLoaderTest {

	@Autowired
	private TaskDataLoader taskDataLoader;

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
		taskDataLoader.loadData(1,taskData);
		assertEquals(operation, taskDataLoader.getLoadedTaskData().getOperation());
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
		taskDataLoader.loadData(1,taskData);
		ArrayList<Integer> loadedNumbers = new ArrayList<Integer>(taskDataLoader.getLoadedTaskData().getNumbers());
		for (int i = 0; i < 5; i++) {
			if (numbers.get(i) != loadedNumbers.get(i)) {
				fail("Numbers are different");
			}
		}
	}

}
