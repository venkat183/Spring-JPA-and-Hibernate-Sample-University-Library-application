package com.venkat.jpa.hibernate.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee {
	private BigDecimal salary;
	

	protected FullTimeEmployee() {
		super();
	}

	public FullTimeEmployee(String name,BigDecimal salary) {
		super(name);
		this.salary = salary;
	}

	public BigDecimal getsalary() {
		return salary;
	}

	public void setsalary(BigDecimal salary) {
		this.salary = salary;
	}

}
