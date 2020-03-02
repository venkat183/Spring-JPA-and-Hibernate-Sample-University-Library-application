package com.venkat.jpa.hibernate.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class TypedQueryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void typedTestCase_select_allCourses() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		Root<Course> from = cq.from(Course.class);
		TypedQuery<Course> createQuery = em.createQuery(cq.select(from));
		List<Course> resultList = createQuery.getResultList();
		logger.info("select c from Course c Typed->{}", resultList);
	}

	@Test
	public void typedTestCase_Select_Specific_Course() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		Root<Course> from = cq.from(Course.class);
		Predicate like = cb.like(from.get("name"), "%Steps");
		CriteriaQuery<Course> where = cq.where(like);
		TypedQuery<Course> createQuery = em.createQuery(cq.select(from));
		List<Course> resultList = createQuery.getResultList();
		logger.info("Specific type of Course->{}", resultList);
	}

	@Test
	public void typedTestCase_Select_Specific_Course_WithOut_Students() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		Root<Course> from = cq.from(Course.class);
		Predicate like = cb.isEmpty(from.get("students"));
		CriteriaQuery<Course> where = cq.where(like);
		TypedQuery<Course> createQuery = em.createQuery(cq.select(from));
		List<Course> resultList = createQuery.getResultList();
		logger.info("Course w/o Students->{}", resultList);
	}

	@Test
	public void inner_join_Test() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		Root<Course> from = cq.from(Course.class);
		from.join("students");
		TypedQuery<Course> createQuery = em.createQuery(cq.select(from));
		List<Course> resultList = createQuery.getResultList();
		logger.info("Course inner join->{}", resultList);
	}

	@Test
	public void left_outer_join_Test() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		Root<Course> from = cq.from(Course.class);
		Join<Object, Object> join = from.join("students",JoinType.LEFT);
		TypedQuery<Course> createQuery = em.createQuery(cq.select(from));
		List<Course> resultList = createQuery.getResultList();
		logger.info("Course -> left outter join->{}", resultList);
	}
}
