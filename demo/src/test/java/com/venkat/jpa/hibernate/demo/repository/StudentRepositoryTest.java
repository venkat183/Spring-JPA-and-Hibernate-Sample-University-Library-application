package com.venkat.jpa.hibernate.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.venkat.jpa.hibernate.demo.DemoApplication;
import com.venkat.jpa.hibernate.demo.entity.Course;
import com.venkat.jpa.hibernate.demo.entity.Passport;
import com.venkat.jpa.hibernate.demo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class StudentRepositoryTest {
	 private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository st;

	@Autowired
	EntityManager em;

	@Test
	@Transactional
	public void findById_TestCase() {
		Student student = em.find(Student.class,20001L);
		logger.info("Student is->{}",student);
		logger.info("Passport is->{}",student.getPassport());		
	}
	
	@Test
	@Transactional
	public void findByIdPassport_TestCase() {
		Passport passport = em.find(Passport.class,40001L);
		logger.info("Passport is->{}",passport);
		logger.info("But it's student would be is->{}",passport.getStudent());		
	}
}
