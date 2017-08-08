package com.project.first;

import org.springframework.http.ResponseEntity;

/**
 * Executes a task by given id.
 *
 */
public interface TaskExecutor {
	/**
	 * Executes a task by given id.
	 * 
	 * @param id
	 *            a unique id of task to execute
	 */
	public void executeTask(long id);
}
