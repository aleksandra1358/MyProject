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
	 */
	public void loadData(TaskData taskData);

	/**
	 * Returns loaded task data
	 * 
	 * @return task data
	 */
	public TaskData getLoadedTaskData();
}
