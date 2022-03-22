package ca.sheridancollege.bichl.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.bichl.Addition.StudentUserDetails;
import ca.sheridancollege.bichl.beans.CartEvent;
import ca.sheridancollege.bichl.beans.Event;
import ca.sheridancollege.bichl.beans.User;
import ca.sheridancollege.bichl.repositories.CartEventRepository;
import ca.sheridancollege.bichl.services.CartEventServices;
import ca.sheridancollege.bichl.services.UserService;

@Controller
public class CartEventController {

	@Autowired
	private CartEventServices carteventservices;
	
	@Autowired
	private CartEventRepository cartEventRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/cart")
	public String showEventCart( @AuthenticationPrincipal StudentUserDetails studentDetails,@AuthenticationPrincipal Authentication authentication,Model model) {
		
		//User user = userService.getUserByEmail(studentEmail);
		//authentication.isAuthenticated();
		User user = userService.getCurrentlyLoggedInUser(authentication);
		List<CartEvent> cartEvents = cartEventRepository.findByUser(user);

		model.addAttribute("user",user);
		model.addAttribute("cartEvents",cartEvents);
		model.addAttribute("pageTitle","Event Cart");
		
		//System.out.printf(user.getEmail().toString());
		
		return "eventCart";
	}
}
