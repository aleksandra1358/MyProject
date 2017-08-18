package com.project.first;

import java.util.ArrayList;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;

public class SimpleTaskExecutor implements TaskExecutor {

	@Autowired
	private TaskService taskService;

	private final ArrayList<Result> results = new ArrayList<>();

	@Override
	public void executeTask(long id) {
		Result result = new Result();
		Task task = taskService.findTaskById(id);
		TaskData taskData = task.getTaskData();
		result.setId(id);
		switch (taskData.getOperation()) {
		case ADD:
			result.setValue(add(new ArrayList<>(taskData.getNumbers())));
			results.add(result);
			break;
		case SUBTRACT:
			result.setValue(subtract(new ArrayList<>(taskData.getNumbers())));
			results.add(result);
			break;
		case MULTIPLY:
			result.setValue(multiply(new ArrayList<>(taskData.getNumbers())));
			results.add(result);
			break;
		case DIVIDE:
			result.setValue(divide(new ArrayList<>(taskData.getNumbers())));
			results.add(result);
			break;
		default:
			break;

		}

	}

	@Override
	public double downloadResult(long id) {
		for (Result result : results) {
			if (result.getId() == id) {
				return result.getValue();
			}

		}
		throw new NoResultException();

	}
	
	@Override
	public boolean checkTask(long id) {
		for (Result result : results) {
			if (result.getId() == id) {
				return true;
			}
		}
		return false;
	}

	private double add(ArrayList<Integer> numbers) {
		noNumber(numbers);
		double score = 0;
		for (Integer number : numbers) {
			score = score + number;
		}
		return score;
	}

	private double subtract(ArrayList<Integer> numbers) {
		noNumber(numbers);
		double score = numbers.get(0);
		for (Integer number : numbers) {
			score = score - number;
		}
		score = score + numbers.get(0);
		return score;
	}

	private double multiply(ArrayList<Integer> numbers) {
		noNumber(numbers);
		double score = 1;
		for (Integer number : numbers) {
			score = score * number;
		}
		return score;
	}

	private double divide(ArrayList<Integer> numbers) {
		noNumber(numbers);
		double score = numbers.get(0);
		for (int i = 1; i < numbers.size(); i++) {
			if (numbers.get(i) != 0) {
				score = score / numbers.get(i);
			} else {
				throw new IllegalArgumentException();
			}

		}
		return score;
	}

	private void noNumber(ArrayList<Integer> numbers) {
		if (numbers.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	

}
