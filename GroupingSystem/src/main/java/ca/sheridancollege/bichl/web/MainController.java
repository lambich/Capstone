package ca.sheridancollege.bichl.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.bichl.service.EventService;

@Controller
public class MainController {
	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/login")
	public String login() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			
			return "login";
		}
		return "redirect:/";
		
	}
	
	//@GetMapping("/")
	//public String home(Model model) {
	//	model.addAttribute("listEvent", eventService.getAllEvents());
	//	return "index";

	//}
} 