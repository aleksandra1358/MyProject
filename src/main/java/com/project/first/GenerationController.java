package com.project.first;

import com.project.first.task.Task;
import com.project.first.task.TaskRepository;
import com.project.first.task.TaskService;
import com.project.first.taskdata.TaskData;
import com.project.first.taskdata.TaskDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GenerationController
{
    private final TaskService service;
    private final TaskDataLoader taskDataLoader;

    @Autowired
    public GenerationController(TaskService service, TaskDataLoader taskDataLoader)
    {
        this.service = service;
        this.taskDataLoader = taskDataLoader;
    }

    @PostMapping("tasksGenerator")
    public void generateTasks(@RequestParam(value = "amount") int amount, @RequestBody TaskData taskData) {
        for(int i = 1; i <= amount; i++)
        {
            Task task = service.produceTask();
            taskDataLoader.addTaskDataToTask(task.getId(), taskData);
        }
    }
}
