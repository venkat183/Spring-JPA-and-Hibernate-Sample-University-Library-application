package com.venkat.jpa.hibernate.demo.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import com.venkat.jpa.hibernate.demo.DemoApplication;
import com.venkat.jpa.hibernate.demo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseSpringDataRepository cr;

	@Test
	public void course_isPresent() {
		Optional<Course> findById = cr.findById(10001L);
		assertTrue(findById.isPresent());
	}

	@Test
	public void course_isNotPresent() {
		Optional<Course> findById = cr.findById(50001L);
		assertFalse(findById.isPresent());
	}

	@Test
	public void playingAroundWithSpringDataJPA() {
		Course course = new Course("Microservices in 100 Steps");
		cr.save(course);
		course.setName("Microservices in 100 Steps-Updated");
		cr.save(course);
		logger.info("All courses ->{} ", cr.findAll());
		logger.info("Count of courses ->{} ", cr.count());
	}

	@Test
	public void sort() {
		Sort sort = new Sort(Sort.Direction.DESC, "name");
		logger.info("sorted ->{} ", cr.findAll(sort));
	}

	@Test
	public void pagination() {
		PageRequest pr = PageRequest.of(0, 3);
		Page<Course> pageOne = cr.findAll(pr);
		logger.info("pageOne ->{} ", pageOne.getContent());
		Pageable nextPageable = pageOne.nextPageable();
		Page<Course> pageTwo = cr.findAll(nextPageable);
		logger.info("pageTwo ->{} ", pageTwo.getContent());

	}
	
	@Test
	public void findByNameTest() {
		logger.info("By Name 'Python' ->{} ", cr.findByName("Python with flask"));
	}
}