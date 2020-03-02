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
import com.venkat.jpa.hibernate.demo.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository cr;

	@Autowired
	EntityManager em;

	@Test
	@Transactional
	public void findById_TestCase() {
		Course course = cr.findById(10001L);
		logger.info("Course retrieved is ->{}",course);
		Course course1 = cr.findById(10001L);
		logger.info("Course retrieved again  is ->{}",course1);
		assertEquals("Python with flask", course.getName());
		assertEquals("Python with flask", course1.getName());
	}

	
	@Test
	@DirtiesContext
	public void save_TestCase() {
		Course course = cr.findById(10001L);
		assertEquals("Python with flask", course.getName());
		course.setName("Python with Django now");
		cr.save(course);
		Course course2 = cr.findById(10001L);
		assertEquals("Python with Django now", course2.getName());

	}

	@Test
	@Transactional
	public void ManyToOneLazyFetch_TestCase() {
		Course course = cr.findById(10003L);
		logger.info("111->{}", course);
		logger.info("222->{}", course.getReviews());
	}

	@Test
	@Transactional
	public void ManyToOneEagerFetch_TestCase() {
		Review review = em.find(Review.class, 50001L);
		logger.info("111->{}", review.getCourse());
	}

}
