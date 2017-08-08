package com.project.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducingController {
	
	@Autowired
	TaskService service;
	
}


//implements TaskService {
//
//	@Override
//	public long produceTask(String description) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public Task findTaskById(long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
