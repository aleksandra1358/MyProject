package com.project.first;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TASK_DATA")
public class TaskData {

	@Id
	@Column(name = "TASK_DATA_ID")
	private Long id;

	@ElementCollection
	@CollectionTable(name = "NUMBERS")
	private List<Integer> numbers;

	@Column(name = "OPERATION")
	private Operation operation;

	@OneToOne(mappedBy = "taskData")
	private Task task;

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

}
