package ca.sheridancollege.bichl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.bichl.model.Course;
import ca.sheridancollege.bichl.repository.CourseRepository;
import ca.sheridancollege.bichl.repository.UserRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Course> findAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public void saveCourse(Course course, Long id) {
		courseRepository.save(course);
		userRepository.getById(id).getCourseList().add(course);
		}

	
	@Override
	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);

	}

	@Override
	public Course getCourseById(Long id) {
		return courseRepository.getById(id);
	}

}
