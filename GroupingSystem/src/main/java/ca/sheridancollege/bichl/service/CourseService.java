package ca.sheridancollege.bichl.service;

import java.util.List;

import ca.sheridancollege.bichl.model.Course;

public interface CourseService {

	List<Course> findAllCourses();
	
	void saveCourse(Course course, Long id);
	
	void deleteCourse(Long id);
	
	Course getCourseById(Long id);
}
