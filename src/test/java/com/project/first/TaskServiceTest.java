package com.project.first;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TaskServiceTest {

	private TaskService taskService;

	@Before
	public void setup() {
		taskService = null;
	}

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
}
