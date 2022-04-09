package ca.sheridancollege.bichl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.bichl.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
