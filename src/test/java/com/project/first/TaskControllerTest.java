package com.project.first;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerTest {

	@Autowired
	private TestRestTemplate template;

	@Test
	public void produceTask_withAuthorization() {
		ResponseEntity<String> response = template.withBasicAuth("user", "pas")
				.getForEntity("/tasks", String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void produceTask_withoutAuthorization() {
		ResponseEntity<String> response = template.getForEntity("/tasks", String.class);
		Assert.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}

}
