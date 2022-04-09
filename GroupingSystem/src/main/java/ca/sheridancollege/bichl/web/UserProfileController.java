package ca.sheridancollege.bichl.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.bichl.model.Course;
import ca.sheridancollege.bichl.model.User;
import ca.sheridancollege.bichl.repository.CourseRepository;
import ca.sheridancollege.bichl.service.CourseServiceImpl;
import ca.sheridancollege.bichl.service.UserService;


@Controller
public class UserProfileController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private CourseServiceImpl courseServiceImpl;
	
	
	private UserDetails userDetails;
	private User user;
	
	@GetMapping("userProfile")
	public String userProfile(Model model) {
		this.userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.user = userService.getUserByEmail(userDetails.getUsername());
		model.addAttribute("courseList", user.getCourseList());
		model.addAttribute("originalUser", user);
		return ("userProfile");
	}
	
	@GetMapping("userProfileEdit")
	public String userProfileEdit(Model model) {
		this.userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.user = userService.getUserByEmail(userDetails.getUsername());
		model.addAttribute("originalUser", user);
		model.addAttribute("user", new User());
		model.addAttribute("course", new Course());
		return ("userProfileEdit");
	}
	
	@PostMapping("userProfileEdit")
	public String userProfileEditSubmit(@ModelAttribute User user, Model model, @ModelAttribute Course course) {
			User originalUser = userService.getUserByEmail(userDetails.getUsername());
			originalUser.setFirstName(user.getFirstName());
			originalUser.setLastName(user.getLastName());
			//originalUser.setProgram(user.getProgram());
			userService.updateUser(originalUser);
			model.addAttribute("originalUser", this.user);
			return ("userProfile");
	}
	
	@PostMapping("addCourse")
	public String addCourse(@ModelAttribute("course") Course course, Model model) {
		courseServiceImpl.saveCourse(course, this.user.getId());
		User originalUser = this.user;
		model.addAttribute("originalUser", originalUser);
		model.addAttribute("user", new User());
		model.addAttribute("course", new Course());
		return ("userProfile");
	}
	
}
