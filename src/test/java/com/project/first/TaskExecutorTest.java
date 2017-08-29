package com.project.first;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.first.task.Task;
import com.project.first.task.TaskService;
import com.project.first.taskdata.TaskData;
import com.project.first.taskdata.TaskDataLoader;
import com.project.first.taskexecutor.TaskExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskExecutorTest {

	@Autowired
	TaskExecutor taskExecutor;

	@Autowired
	TaskService taskService;

	@Autowired
	TaskDataLoader taskDataLoader;

	@Test
	public void executeTask_Add() {
		verifyExecutor(Operation.ADD, 6, 2, 1, 3);
	}

	@Test
	public void executeTask_AddOnlyOneItem() {
		verifyExecutor(Operation.ADD, 1, 1);
	}

	@Test
	public void executeTask_AddNegativeItem() {
		verifyExecutor(Operation.ADD, 1, -5, 6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void executeTask_AddNoNumbers() {
		Task task = createTask();
		createTaskData(task, Operation.ADD);
		taskExecutor.executeTask(task.getId());
	}

	@Test
	public void executeTask_Subtract() {
		verifyExecutor(Operation.SUBTRACT, 3, 5, 2);
	}

	@Test
	public void executeTask_SubtractOnlyOneItem() {
		verifyExecutor(Operation.SUBTRACT, 2, 2);
	}

	@Test
	public void executeTask_SubtractNegativeItem() {
		verifyExecutor(Operation.SUBTRACT, 4, 2, -2);
	}

	@Test
	public void executeTask_SubtractFromNegativeItem() {
		verifyExecutor(Operation.SUBTRACT, -4, -2, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void executeTask_SubtractNoNumbers() {
		Task task = createTask();
		createTaskData(task, Operation.SUBTRACT);
		taskExecutor.executeTask(task.getId());
	}

	@Test
	public void executeTask_Multiply() {
		verifyExecutor(Operation.MULTIPLY, 6, 1, 2, 3);
	}

	@Test
	public void executeTask_MultiplyOnlyOneItem() {
		verifyExecutor(Operation.MULTIPLY, 3, 3);
	}

	@Test
	public void executeTask_MultiplyNegativeItem() {
		verifyExecutor(Operation.MULTIPLY, -2, -1, 2);
	}

	@Test
	public void executeTask_MultiplyTwoNegativeItems() {
		verifyExecutor(Operation.MULTIPLY, 2, -1, -2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void executeTask_MultiplyNoNumbers() {
		Task task = createTask();
		createTaskData(task, Operation.MULTIPLY);
		taskExecutor.executeTask(task.getId());
	}

	@Test
	public void executeTask_Divide() {
		verifyExecutor(Operation.DIVIDE, 5, 10, 2);
	}

	@Test
	public void executeTask_DivideOnlyOneItem() {
		verifyExecutor(Operation.DIVIDE, 1, 1);
	}

	@Test
	public void executeTask_DivideNegativeItem() {
		verifyExecutor(Operation.DIVIDE, -5, 10, -2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void executeTask_DivideNoNumbers() {
		Task task = createTask();
		createTaskData(task, Operation.DIVIDE);
		taskExecutor.executeTask(task.getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void executeTask_DivideByZero() {
		Task task = createTask();
		createTaskData(task, Operation.DIVIDE, 1, 0);
		taskExecutor.executeTask(task.getId());
	}

	@Test(expected = NoResultException.class)
	public void downloadResult_NoResult() {
		Task task = createTask();
		task.setResult(null);
		taskExecutor.downloadResult(task.getId());
	}

	@Test
	public void checkTask_existResult() {

		Task task = createTask();
		createTaskData(task, Operation.ADD, 1, 2, 3);
		taskExecutor.executeTask(task.getId());
		assertTrue(taskExecutor.checkTask(task.getId()));
	}
	
	@Test
	public void checkTask_NoResult() {
		Task task = createTask();
		createTaskData(task, Operation.ADD, 1, 2, 3);
		assertFalse(taskExecutor.checkTask(task.getId()));
	}

//  todo original test, below is mine async verify executor.
//	private void verifyExecutor(Operation operation, double expectedResult, Integer... values) {
//		Task task = createTask();
//		createTaskData(task, operation, values);
//
//		taskExecutor.executeTask(task.getId());
//		double result = taskExecutor.downloadResult(task.getId());
//
//		Assert.assertEquals(expectedResult, result, 0);
//	}

    private void verifyExecutor(Operation operation, double expectedResult, Integer... values) {
        Task task = createTask();
        createTaskData(task, operation, values);
        Future<String> future;

        future = taskExecutor.executeTask(task.getId());
        try
        {
            future.get();
        }
        catch(InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
        double result = taskExecutor.downloadResult(task.getId());

        Assert.assertEquals(expectedResult, result, 0);
    }

	private TaskData createTaskData(Task task, Operation operation, Integer... values) {
		TaskData taskData = new TaskData();
		taskData.setNumbers(Arrays.asList(values));
		taskData.setOperation(operation);
		taskDataLoader.addTaskDataToTask(task.getId(), taskData);
		return taskData;
	}

	private Task createTask() {
		long id = taskService.produceTask("test");
		return taskService.findTaskById(id);
	}
}
