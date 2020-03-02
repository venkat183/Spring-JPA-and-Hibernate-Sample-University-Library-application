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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class NativeTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void jpqlTestCase() {
		Query createNativeQuery = em.createNativeQuery("select * from Course",Course.class);
		List resultList = createNativeQuery.getResultList();
		logger.info("select * from Course c->{}", resultList);
	}
	
	@Test
	public void jpqlTestCase_with_QueryParams() {
		Query createNativeQuery = em.createNativeQuery("select * from Course where id=?",Course.class);
		createNativeQuery.setParameter(1, 10001L);
		List resultList = createNativeQuery.getResultList();
		logger.info("select * from Course where id= 10001L->{}", resultList);
	}
   
	@Test
	public void jpqlTestCase_with_Named_Params() {
		Query createNativeQuery = em.createNativeQuery("select * from Course where id=:id",Course.class);
		createNativeQuery.setParameter("id", 1L);
		List resultList = createNativeQuery.getResultList();
		logger.info("select * from Course where id= 1L->{}", resultList);
	}

}
