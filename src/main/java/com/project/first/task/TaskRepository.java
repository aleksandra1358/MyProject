package com.project.first.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
	Task findById(Long id);
	List<Task> findAll();
}
