package com.project.first;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatingController {
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/creating")
	public long createTask(@RequestParam(value="description", defaultValue="standard")String description) {
		return new Task(counter.incrementAndGet(),description).getId();
	}
}
