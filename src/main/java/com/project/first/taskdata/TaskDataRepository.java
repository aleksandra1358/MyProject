package com.project.first.taskdata;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDataRepository extends JpaRepository<TaskData, Long> {
	TaskData findById(Long id);
}
