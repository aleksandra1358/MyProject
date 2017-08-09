package com.project.first;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
	Task findById(Long id);
	Task save(Task task);
}
