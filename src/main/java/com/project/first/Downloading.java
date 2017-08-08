package com.project.first;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

public interface Downloading {

	public ResponseEntity<ArrayList<Task>> downloadExistingTasks();
}
