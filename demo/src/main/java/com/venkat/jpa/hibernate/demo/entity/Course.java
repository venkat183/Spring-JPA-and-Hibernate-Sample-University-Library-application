package com.venkat.jpa.hibernate.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries(value = {
		@NamedQuery(name="query_get_all_Courses",query = "select c from Course c"),
		@NamedQuery(name="query_get_all_courses_Brad",query="select c from Course c where name like '%Brad'")	
})
@Cacheable
public class Course {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false)
	private String name;
	
	@OneToMany(mappedBy="course")
	private List<Review> reviews = new ArrayList<Review>();
	
	@CreationTimestamp
	private LocalDateTime creationDate;
	@UpdateTimestamp
	private LocalDateTime UpdationDate;
	
	@ManyToMany(mappedBy="courses")
	@JsonIgnore
	List<Student> students = new ArrayList<>();

	protected Course() {

	}
	
	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
	

	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	@Override
	public String toString() {
		return "Course [name=" + name + "]";
	}

}
