package com.venkat.jpa.hibernate.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.venkat.jpa.hibernate.demo.entity.Course;

@RepositoryRestResource(path="course")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long>{
      List<Course> findByName(String name);
}
