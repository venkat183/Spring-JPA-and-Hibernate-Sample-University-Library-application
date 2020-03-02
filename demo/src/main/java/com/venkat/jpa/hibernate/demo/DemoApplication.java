package com.venkat.jpa.hibernate.demo;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.venkat.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.venkat.jpa.hibernate.demo.entity.PartTimeEmployee;
import com.venkat.jpa.hibernate.demo.repository.CourseRepository;
import com.venkat.jpa.hibernate.demo.repository.EmployeeRepository;
import com.venkat.jpa.hibernate.demo.repository.StudentRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	CourseRepository cr;

	@Autowired
	StudentRepository sr;

	@Autowired
	EmployeeRepository er;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * List<Review> reviews=new ArrayList<Review>(); reviews.add(new
		 * Review("4","Good Job Mosh!")); reviews.add(new
		 * Review("5","Awesome Job Mosh!"));
		 */
		/*
		 * Course course = cr.findById(10001L); Course newCourse = cr.save(new
		 * Course("Node JS by Mosh Hamedani"));
		 * logger.info("Course 10001 is ->{}",course);
		 * logger.info("The new course created is->{}",newCourse);
		 */
		// cr.playWithEntityManager();
		// sr.saveStudentWithPassport();
		// cr.addReviews();
		// cr.addReviewsGeneric(10003L, reviews);
		// sr.insertStudentandCourse();

		/*er.saveEmployee(new PartTimeEmployee("Jack", new BigDecimal("50")));
		er.saveEmployee(new FullTimeEmployee("Jill", new BigDecimal("10000")));
		logger.info("List of PartTime Employees are->{}", er.getPartTimeEmployees());
		logger.info("List of FullTime Employees are->{}", er.getFullTimeEmployees());*/

	}

}
