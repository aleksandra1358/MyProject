//package com.project.first.taskexecutor;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.NoResultException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.project.first.result.Result;
//import com.project.first.task.Task;
//import com.project.first.task.TaskService;
//import com.project.first.taskdata.TaskData;
//
//public class SimpleTaskExecutor implements TaskExecutor {
//
//	@Autowired
//	private TaskService taskService;
//
//	private List<Result> results;
//
//	@PostConstruct
//	public void init() {
//		results = new ArrayList<>();
//	}
//
//	@Override
//	public void executeTask(long id) {
//		Task task = taskService.findTaskById(id);
//		TaskData taskData = task.getTaskData();
//		Result result = new Result();
//		result.setId(id);
//		switch (taskData.getOperation()) {
//		case ADD:
//			result.setValue(add(taskData.getNumbers()));
//			results.add(result);
//			break;
//		case SUBTRACT:
//			result.setValue(subtract(taskData.getNumbers()));
//			results.add(result);
//			break;
//		case MULTIPLY:
//			result.setValue(multiply(taskData.getNumbers()));
//			results.add(result);
//			break;
//		case DIVIDE:
//			result.setValue(divide(taskData.getNumbers()));
//			results.add(result);
//			break;
//		default:
//			break;
//
//		}
//
//	}
//
//	@Override
//	public double downloadResult(long id) {
//		for (Result result : results) {
//			if (result.getId() == id) {
//				return result.getValue();
//			}
//
//		}
//		throw new NoResultException();
//
//	}
//
//	@Override
//	public boolean checkTask(long id) {
//		for (Result result : results) {
//			if (result.getId() == id) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	private double add(Collection<Integer> numbers) {
//		verifyContent(numbers);
//		double score = 0;
//		for (Integer number : numbers) {
//			score = score + number;
//		}
//		return score;
//	}
//
//	private double subtract(List<Integer> numbers) {
//		verifyContent(numbers);
//		double score = numbers.get(0);
//		for (Integer number : numbers) {
//			score = score - number;
//		}
//		return score + numbers.get(0);
//	}
//
//	private double multiply(Collection<Integer> numbers) {
//		verifyContent(numbers);
//		double score = 1;
//		for (Integer number : numbers) {
//			score = score * number;
//		}
//		return score;
//	}
//
//	private double divide(List<Integer> numbers) {
//		verifyContent(numbers);
//		double score = numbers.get(0);
//		for (int i = 1; i < numbers.size(); i++) {
//			if (numbers.get(i) != 0) {
//				score = score / numbers.get(i);
//			} else {
//				throw new IllegalArgumentException();
//			}
//
//		}
//		return score;
//	}
//
//	private void verifyContent(Collection<Integer> numbers) {
//		if (numbers.isEmpty()) {
//			throw new IllegalArgumentException("You cannot divide by zero");
//		}
//	}
//
//}
