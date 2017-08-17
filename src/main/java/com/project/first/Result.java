package com.project.first;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public void setId(Long id) {
		if(this.id==null) {
			this.id = id;
		}
		
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	
}
