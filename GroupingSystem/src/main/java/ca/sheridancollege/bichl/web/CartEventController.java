package ca.sheridancollege.bichl.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import ca.sheridancollege.bichl.Addition.StudentUserDetails;
import ca.sheridancollege.bichl.model.CartEvent;
import ca.sheridancollege.bichl.model.Event;
import ca.sheridancollege.bichl.model.User;
import ca.sheridancollege.bichl.repository.CartEventRepository;
import ca.sheridancollege.bichl.service.CartEventServices;
import ca.sheridancollege.bichl.service.UserService;

@Controller
public class CartEventController {

	@Autowired
	private CartEventServices carteventservices;
	
	@Autowired
	private CartEventRepository cartEventRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/cart")
	public ModelAndView showEventCart(Model model,@AuthenticationPrincipal Authentication authentication) {
		ModelAndView eventCart = new ModelAndView("eventCart");
		ModelAndView index = new ModelAndView("index");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		User user = userService.getUserByEmail(auth.getName());
		
		//User user = userService.getCurrentlyLoggedInUser(authentication);
		//User user = userService.getCurrentlyLoggedInUser(authentication);
		if (user == null)
		{
			return index;
		}
		List<CartEvent> cartEvents = carteventservices.listCartEvents(user);

		model.addAttribute("user",user);
		model.addAttribute("cartEvents",cartEvents);
		model.addAttribute("pageTitle","Event Cart");
		
		//System.out.printf(user.getEmail().toString());
		
		return eventCart;
	}
	
	@GetMapping("/removeEventFromCart/{id}")
	public String deleteEvent(@PathVariable (value="id") long id) {
		//delete event from service
		//this.cartEventServices.
		this.carteventservices.removeEventFromCart(id);
		return "redirect:/";
	}
}
