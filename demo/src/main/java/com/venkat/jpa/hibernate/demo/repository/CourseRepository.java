package com.venkat.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.venkat.jpa.hibernate.demo.entity.Course;
import com.venkat.jpa.hibernate.demo.entity.Review;

@Repository
@Transactional
public class CourseRepository {
	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {
		if (course.getId() == null) {
			em.persist(course);
		} else
			em.merge(course);

		return course;

	}

	public void deleteById(Long id) {
		Course cr = em.find(Course.class, id);
		em.remove(cr);
	}

	public void playWithEntityManager() {
		Course c1 = new Course("Python by Jose Portillia");
		em.persist(c1);
		Course c2 = findById(10001L);
		c2.setName("Python with flask-Updated");
	}

	public void addReviews() {
		Course cr = findById(10003L);
		logger.info("Course review are->{}", cr.getReviews());
		Review r1 = new Review("4", "Not a bad course");
		cr.setReviews(r1);
		r1.setCourse(cr);
		Review r2 = new Review("5", "A good course on Node!");
		cr.setReviews(r2);
		r2.setCourse(cr);
		em.persist(r1);
		em.persist(r2);
		logger.info("Course review 22 are->{}", cr.getReviews());
	}

	public void addReviewsGeneric(Long id, List<Review> reviews) {
		Course cr = findById(id);
		for (Review rev : reviews) {
			cr.setReviews(rev);
			rev.setCourse(cr);
			em.persist(rev);
		}
	}

}
