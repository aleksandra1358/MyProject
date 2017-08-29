package com.project.first.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.first.task.Task;

@Entity
@Table(name = "RESULT")
public class Result {

	@Id
	@Column(name = "RESULT_ID")
	private Long id;

	@Column(name = "RESULT_VALUE")
	private double value;

	public Long getId() {
		return id;
	}

	public void setTask(Task id) {
		if (this.id == null) {
			this.id = id.getId();
		}

	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
