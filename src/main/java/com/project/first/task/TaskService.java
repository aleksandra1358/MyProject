package com.project.first.task;

import java.util.List;

/**
 * Creates a new task.
 */
public interface TaskService {

	/**
	 * Creates a new task and finds task by given ID.
	 * 
	 * @param description
	 *            a description, can be null or empty.
	 * @return a unique ID of newly created task, greater than zero
	 */
	Task produceTask(String description);

	/**
	 * Create new task, allow to make task lists from task service.
	 * 
	 * @return created task
	 */
	Task produceTask();

	/**
	 * 
	 * @param id
	 *            a unique ID of task, positive
	 * @return task for given ID
	 */
	Task findTaskById(long id);

	/**
	 * Downloads all existing tasks.
	 * 
	 * @return list of all existing tasks
	 */
	List<Task> downloadExistingTasks();

}
