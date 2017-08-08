package com.project.first;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducingController implements Producing {

	@Override
	public long produceTask(String description) {
		// TODO Auto-generated method stub
		return 0;
	}

}
