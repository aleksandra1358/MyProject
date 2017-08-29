package com.project.first.taskexecutor;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.project.first.Operation;
import com.project.first.result.Result;
import com.project.first.result.ResultRepository;
import com.project.first.task.Task;
import com.project.first.task.TaskRepository;
import com.project.first.taskdata.TaskData;

/**
 * This class needs to be a @Service for @Async to work.
 */
@Service
public class DatabaseTaskExecutor implements TaskExecutor {

	private static final Logger LONG = LoggerFactory.getLogger(DatabaseTaskExecutor.class);

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ResultRepository resultRepository;

	private Map<Operation, Function<Task, Result>> map;

	@PostConstruct
	public void init() {
		map = new HashMap<>();
		map.put(Operation.ADD, (task) -> {
			Result result = new Result();
			result.setTask(task);
			result.setValue(add(task.getTaskData().getNumbers()));
			return result;
		});
	}

	@Async
	public void executeTask2(long id) {
		Task task = findTask(id);
		Function<Task, Result> function = map.get(Operation.ADD);
		Result apply = function.apply(task);
		resultRepository.save(apply);
	}

	@Async
	@Override
	public Future<String> executeTask(long id) {

		LONG.info(String.format("Executing task %d", id));
		Task task = taskRepository.findById(id);
		TaskData taskData = task.getTaskData();

		Result result = new Result();
		result.setTask(task);
		switch (taskData.getOperation()) {
		case ADD:
			result.setValue(add(taskData.getNumbers()));
			resultRepository.save(result);
			break;
		case SUBTRACT:
			result.setValue(subtract(taskData.getNumbers()));
			resultRepository.save(result);
			break;
		case MULTIPLY:
			result.setValue(multiply(taskData.getNumbers()));
			resultRepository.save(result);
			break;
		case DIVIDE:
			result.setValue(divide(taskData.getNumbers()));
			resultRepository.save(result);
			break;
		default:
			break;

		}
		return new AsyncResult<>("Success");
	}

	@Override
	public double downloadResult(long id) {
		if (resultRepository.findById(id) == null) {
			throw new NoResultException(String.format("No result for id: %d", id));
		}
		return resultRepository.findById(id).getValue();
	}

	@Override
	public boolean checkTask(long id) {
		return resultRepository.findById(id) != null;
	}

	private double add(Collection<Integer> numbers) {
		verifyContent(numbers);
		double score = 0;
		for (Integer number : numbers) {
			score += number;
		}
		return score;
	}

	private double subtract(List<Integer> numbers) {
		verifyContent(numbers);
		double score = numbers.get(0);
		for (Integer number : numbers) {
			score -= number;
		}
		return score + numbers.get(0);
	}

	private double multiply(Collection<Integer> numbers) {
		verifyContent(numbers);
		double score = 1;
		for (Integer number : numbers) {
			score = score * number;
		}
		return score;
	}

	private double divide(List<Integer> numbers) {
		verifyContent(numbers);
		double score = numbers.get(0);
		for (int i = 1; i < numbers.size(); i++) {
			if (numbers.get(i) != 0) {
				score = score / numbers.get(i);
			} else {
				throw new IllegalArgumentException("You cannot divide by zero");
			}

		}
		return score;
	}

	private void verifyContent(Collection<Integer> numbers) {
		if (numbers.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	private Task findTask(long id) {
		return taskRepository.findById(id);

	}
}
