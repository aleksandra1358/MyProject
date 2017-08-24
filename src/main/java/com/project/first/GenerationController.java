package com.project.first;

import com.project.first.task.TaskRepository;
import com.project.first.task.TaskService;
import com.project.first.taskdata.TaskData;
import com.project.first.taskdata.TaskDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.IntStream;

@RestController
public class GenerationController
{
    private final TaskService service;
    private final TaskDataLoader taskDataLoader;
    private final TaskRepository repository;

    @Autowired
    public GenerationController(TaskService service, TaskDataLoader taskDataLoader, TaskRepository repository)
    {
        this.service = service;
        this.taskDataLoader = taskDataLoader;
        this.repository = repository;
    }

    @PostMapping("tasksGenerator")
    public void generateTasks(@RequestParam(value = "amount") int amount, @RequestBody TaskData taskData) {
        IntStream
                .range(0, amount)
                .forEach(n -> taskDataLoader.addTaskDataToTask(service.produceTask().getId(), taskData));
    }

    @DeleteMapping("drop")
    public void deleteAllTasks()
    {
        repository.findAll().forEach(t -> repository.delete(t.getId()));
    }
}
