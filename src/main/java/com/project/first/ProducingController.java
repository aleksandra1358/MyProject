package com.project.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducingController {

	@Autowired
	TaskService service;

	@RequestMapping("/producing")
	public long produceTask(@RequestParam(value = "description", required=false) String description) {
			return service.produceTask(description);
	}

}
