package com.venkat.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.venkat.jpa.hibernate.demo.entity.Course;
import com.venkat.jpa.hibernate.demo.entity.Passport;
import com.venkat.jpa.hibernate.demo.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	@Autowired
	EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public Student save(Student student) {
		if (student.getId() == null) {
			em.persist(student);
		} else
			em.merge(student);

		return student;

	}

	public void deleteById(Long id) {
		Student s = em.find(Student.class, id);
		em.remove(s);
	}

	public void saveStudentWithPassport() {
		Passport p = new Passport("K5774777");
		em.persist(p);
		Student s = new Student("Venky!!");
		s.setPassport(p);
		em.persist(s);
	}

	public void playWithEntityManager() {
		Student s1 = new Student("Python by Jose Portillia");
		em.persist(s1);
		Student s2 = findById(10001L);
		s2.setName("Python with flask-Updated");
	}
	
	public void insertStudentandCourse() {
		Student s =new Student("Ravi");
		Course c = new Course("Spring Boot-100 steps bro");
		em.persist(s);
		em.persist(c);
		s.addCourse(c);
		c.addStudent(s);
		em.persist(s);
		
	}

}
