package com.project.first;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

/**
 * Downloads all existing tasks.
 *
 */
public interface TaskDownloader {
	/**
	 * Downloads all existing tasks.
	 * 
	 * @return list of all existing tasks
	 */
	ResponseEntity<ArrayList<Task>> downloadExistingTasks();
}
