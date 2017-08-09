package com.project.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducingController {

	@Autowired
	TaskService service;

}
