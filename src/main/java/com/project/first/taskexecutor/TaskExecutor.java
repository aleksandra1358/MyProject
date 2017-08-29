package com.project.first.taskexecutor;

import java.util.concurrent.Future;

/**
 * Executes a task by given id. Downloads the result of the task which id is
 * given.
 *
 */
public interface TaskExecutor {
	/**
	 * Executes a task by given id.
	 * 
	 * @param id
	 *            a unique id of task to execute
	 */
//	void executeTask(long id);
	Future<String> executeTask(long id);

	/**
	 * Downloads the result of the task which id is given.
	 * 
	 * @param id
	 *            a unique id of the task
	 * @return the result of the task
	 */
	double downloadResult(long id);
	
	/**
	 * Checks if the result of the task which id is given is ready.
	 * 
	 * @param id
	 *            a unique id of task
	 * @return true if the result of the task is ready, in other case return false
	 */
	boolean checkTask(long id);
}
