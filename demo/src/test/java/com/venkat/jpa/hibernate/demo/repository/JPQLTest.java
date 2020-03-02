package com.venkat.jpa.hibernate.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
import com.venkat.jpa.hibernate.demo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class JPQLTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void jpqlTestCase() {
		List resultList = em.createQuery("select c from Course c").getResultList();
		logger.info("select c from Course c->{}", resultList);
	}

	@Test
	public void jpql_Typed() {
		TypedQuery<Course> createQuery = em.createNamedQuery("query_get_all_Courses", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("select c from Course c Typed->{}", resultList);
	}

	@Test
	public void jpql_whereClause() {
		TypedQuery<Course> createQuery = em.createNamedQuery( "query_get_all_courses_Brad", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("select c from Course c where name like '%Brad'->{}", resultList);
	}
	
	@Test
	public void jpql_Course_withOut_Students() {
		TypedQuery<Course> createQuery = em.createQuery( "Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Courses List is->{}", resultList);
	}
	
	@Test
	public void jpql_Course_with_AtLeast_2_Students() {
		TypedQuery<Course> createQuery = em.createQuery( "Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Courses List is->{}", resultList);
	}
	
	@Test
	public void jpql_Course_OrderBy_SizeOfStudents() {
		TypedQuery<Course> createQuery = em.createQuery( "Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = createQuery.getResultList();
		logger.info("Courses List is->{}", resultList);
	}
	
	@Test
	public void jpql_Student_By_Specific_passport_Number_Pattern() {
		TypedQuery<Student> createQuery = em.createQuery( "Select s from Student s where s.passport.number like '%45%'", Student.class);
		List<Student> resultList = createQuery.getResultList();
		logger.info("Students List is->{}", resultList);
	}
	
	@Test
	public void join() {
		Query createQuery = em.createQuery("Select c,s from Course c JOIN c.students s");
		List<Object[]> resultList = createQuery.getResultList();
		logger.info("Students List is->{}", resultList.size());
		for(Object[] arr:resultList) logger.info("Course {} Student {}", arr[0],arr[1]);
	}
	
	@Test
	public void left_join() {
		Query createQuery = em.createQuery("Select c,s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = createQuery.getResultList();
		logger.info("Students List is->{}", resultList.size());
		for(Object[] arr:resultList) logger.info("Course {} Student {}", arr[0],arr[1]);
	}
	
	@Test
	public void cross_join() {
		Query createQuery = em.createQuery("Select c,s from Course c, Student s");
		List<Object[]> resultList = createQuery.getResultList();
		logger.info("Students List is->{}", resultList.size());
		for(Object[] arr:resultList) logger.info("Course {} Student {}", arr[0],arr[1]);
	}
}
