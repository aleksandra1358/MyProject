package com.project.first;

import static org.junit.Assert.assertNotNull;

import java.util.stream.IntStream;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.first.task.Task;
import com.project.first.task.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanValidationTests {
	@Autowired
	private TaskService taskService;

	@Test
	public void produceTask_withAppropriateLengthDescription_taskCreated() {
		long id = taskService.produceTask("Desc").getId();
		Task task = taskService.findTaskById(id);
		assertNotNull(task);
	}

	@Test(expected = ConstraintViolationException.class)
	public void produceTask_withTooShortDescription_exceptionThrown() {
		taskService.produceTask("d");
	}

	@Test(expected = ConstraintViolationException.class)
	public void produceTask_withTooLongDescription_exceptionThrown() {
		StringBuilder description = new StringBuilder();
		IntStream.range(0, 21).forEach(n -> description.append("a"));
		taskService.produceTask(description.toString());
	}
}
