package com.venkat.jpa.hibernate.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {
	private BigDecimal hourlyWage;
	
	protected PartTimeEmployee() {
		super();
	}

	public PartTimeEmployee(String name,BigDecimal hourlywage) {
		super(name);
		this.hourlyWage = hourlywage;
	}

	public BigDecimal getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(BigDecimal hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

}
