package com.project.first;

/**
 * Downloads the result of the task which id is given.
 *
 */
public interface ResultDownloader {
	/**
	 * Downloads the result of the task which id is given.
	 * 
	 * @param id
	 *            a unique id of the task
	 * @return the result of the task
	 */
	public int downloadResult(long id);
}
