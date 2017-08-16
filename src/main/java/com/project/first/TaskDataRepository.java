package com.project.first;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDataRepository extends JpaRepository<TaskData, Long> {
	TaskData findById(Long id);

	TaskData save(TaskData taskData);
}
