package com.venkat.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.venkat.jpa.hibernate.demo.entity.Employee;
import com.venkat.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.venkat.jpa.hibernate.demo.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {
	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void saveEmployee(Employee e) {
		em.persist(e);
	}
	
	public List<PartTimeEmployee> getPartTimeEmployees(){
		return em.createQuery("Select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
	}
	
	public List<FullTimeEmployee> getFullTimeEmployees(){
		return em.createQuery("Select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
	}

}
