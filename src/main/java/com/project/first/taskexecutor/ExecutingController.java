package com.project.first.taskexecutor;

import com.project.first.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutingController {

	private final TaskExecutor taskExecutor;

	private final TaskRepository repository;

    @Autowired
    public ExecutingController(TaskExecutor taskExecutor, TaskRepository repository)
    {
        this.taskExecutor = taskExecutor;
        this.repository = repository;
    }

	@RequestMapping("/execute")
	public void executeAll() {
		repository.findAll().forEach(task -> {
		    if(task.getTaskData() != null) { taskExecutor.executeTask(task.getId()); }
        });
	}
	
	@RequestMapping("/result")
	public double downloadResult(@RequestParam(value = "id") long id) {
		return taskExecutor.downloadResult(id);
	}
}
