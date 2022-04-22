package ca.sheridancollege.bichl.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ca.sheridancollege.bichl.Addition.StudentUserDetails;
import ca.sheridancollege.bichl.model.User;
import ca.sheridancollege.bichl.service.EventService;
import ca.sheridancollege.bichl.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String login() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		return "redirect:/";
		
	}

} 