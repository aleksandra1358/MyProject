package com.project.first;

import com.project.first.task.Task;
import com.project.first.task.TaskRepository;
import com.project.first.taskexecutor.ExecutingController;
import com.project.first.taskexecutor.TaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasks
{
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private final ExecutingController controller;
    private final TaskRepository repository;
    private final TaskExecutor taskExecutor;
    private final AsyncService asyncService;
//
    @Autowired
    public ScheduledTasks(ExecutingController controller, TaskRepository repository, TaskExecutor taskExecutor, AsyncService asyncService)
    {
        this.controller = controller;
        this.repository = repository;
        this.taskExecutor = taskExecutor;
        this.asyncService = asyncService;
    }

    @Scheduled(fixedRate = 10000)
    public void schedule()
    {
//        for(int i = 0; i < 10; i++)
//        {
//            asyncService.TestService();
//        }
//        repository.findAll().forEach(task -> asyncService.TestService());
        log.info("Execution!");
//        repository.findAll().forEach(task -> taskExecutor.executeTask(task.getId()));
        for(Task task : repository.findAll())
        {
            taskExecutor.executeTask(task.getId());
        }
    }

    //    @Async
//    @Scheduled(fixedRate = 10000)
//    public void executeAllTasksAsync()
//    {
//        log.info("Execution!");
//        controller.executeAll();
//    }

//    @Async
//    @Scheduled(fixedRate = 10000)
//    void execute()
//    {
//        log.info("Execution!");
//        final int[] i = {0};
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        repository.findAll().forEach(task -> {
//            CustomThread worker = new CustomThread(taskExecutor);
//            worker.setTask(task);
//            executor.execute(worker);
//            log.info("Execution " + i[0]);
//            i[0]++;
//        });
//        executor.shutdown();
//    }

//    @Async
//    public void execOne(Task task)
//    {
//        taskExecutor.executeTask(task.getId());
//    }
}
