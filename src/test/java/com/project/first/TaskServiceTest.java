package com.project.first;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.first.task.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

	@Autowired
	private TaskService taskService;

	@Test
	public void produceTask_withDesciption() {
		long id = taskService.produceTask("foo");
		Assert.assertTrue(id > 0);
	}

	@Test
	public void produceTask_withoutDesciption() {
		long id = taskService.produceTask("");
		Assert.assertTrue(id > 0);
	}

	@Test
	public void produceTask_withNullDesciption() {
		long id = taskService.produceTask(null);
		Assert.assertTrue(id > 0);
	}

	@Test
	public void produceTask_isIdUnique() {
		Set<Long> ids = new HashSet<>();
		for (int i = 0; i < 10; i++) {
			ids.add(taskService.produceTask(""));
		}
		if (ids.size() != 10) {
			Assert.fail("Id isn't unique");
		}

	}

	@Test
	public void findTaskById_checkDescription() {
		String description = "des";
		long id = taskService.produceTask(description);
		Assert.assertEquals(taskService.findTaskById(id).getDescription(), description);
	}

	@Test(expected = IllegalArgumentException.class)
	public void findTaskById_withZeroId() {
		taskService.findTaskById(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void findTaskById_withNegativeId() {
		taskService.findTaskById(-3);
	}

	@Test
	public void downloadExistingTasks_quantity() {
		long id = taskService.produceTask("abc");

		Assert.assertTrue(taskService.downloadExistingTasks().size() == id);
	}
}
