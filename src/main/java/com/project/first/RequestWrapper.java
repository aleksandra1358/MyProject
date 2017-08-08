package com.project.first;

import java.util.ArrayList;

public class RequestWrapper {

	private ArrayList<Integer> numbers;
	private Operation operation;

	public ArrayList<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(ArrayList<Integer> numbers) {
		this.numbers = numbers;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

}
