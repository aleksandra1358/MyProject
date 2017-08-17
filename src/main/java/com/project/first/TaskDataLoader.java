package com.project.first;

/**
 * Loads task data.
 *
 */
public interface TaskDataLoader {

	/**
	 * Loads task data.
	 * 
	 * @param taskData
	 *            data of the task
	 * 
	 * @param id
	 *            an id of the task
	 * 
	 */
	void loadData(long id, TaskData taskData);

	/**
	 * Returns loaded task data
	 * 
	 * @return task data
	 */
	TaskData getLoadedTaskData();
}
