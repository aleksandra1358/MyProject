package com.project.first;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
	Task findById(Long id);

	Task save(Task task);
}
