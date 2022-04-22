package ca.sheridancollege.bichl.web;

import java.sql.Blob;
import java.util.Arrays;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ca.sheridancollege.bichl.model.Event;
import ca.sheridancollege.bichl.model.User;
import ca.sheridancollege.bichl.repository.UserRepository;
import ca.sheridancollege.bichl.service.EventService;
import ca.sheridancollege.bichl.service.UserService;

@Controller
public class EventController {

	@Autowired
	private EventService eventService;
	private UserRepository userRepo;
	
	@GetMapping("/events")
	public String home(Model model) {
		return findPaginated(1,"eventName", "asc", model);
	}
	
	//display list of event
	@GetMapping("/addEvent")
	public String showNewEventForm(Model model) {
		//Create model attribute to bind from data
		
		//store image to database
		
		
		List<String> typeOfEventList = Arrays.asList("Online","In Person (Indoor)","In Person (Outdoor)");
		List<String> categoriesList = Arrays.asList("Food", "Music", "Health", "Fashion", "Business", "Sport", "Education", 
				"Art", "Party", "Entertainment", "Others");
		
		model.addAttribute("typeOfEventList",typeOfEventList);
		model.addAttribute("categoriesList",categoriesList);
		
		Event event = new Event();
		model.addAttribute("event", event);
		return "newEvent.html";
	}
	 
	@PostMapping("/addEvent")
    public String addEvent(@ModelAttribute("event") Event event, @RequestParam(value = "image", required = true)MultipartFile file, @AuthenticationPrincipal Authentication authentication){
//		String id = String.valueOf(UUID.randomUUID());
	    Blob blob = null;
	    byte[] blobAsBytes=null;
	    try {
	        blob = new SerialBlob(file.getBytes());
	        
	        int blobLength = (int) blob.length();  
	        blobAsBytes = blob.getBytes(1, blobLength);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    event.setEventImage(blobAsBytes);
	    
//	    Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
//	    System.out.println(auth.getName());
//		User user = userRepo.findByEmail(auth.getName());
//	    event.getAttendees().add(user);
	    
	    System.out.println(event.getEventImage());
	    eventService.save(event);
	    
	    return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") long id, Model model) {
		//get event from service
		Event event = eventService.getEventById(id);
		
		//set event as a model
		model.addAttribute("event",event);
		return "editEvent";
	}
	
	@GetMapping("/deleteEvent/{id}")
	public String deleteEvent(@PathVariable (value="id") long id) {
		//delete event from service
		//this.cartEventServices.
		this.eventService.deleteEventById(id);
		return "redirect:/";
	}
	
	@GetMapping("/addEventToCart/{id}")
	public String addEventToCart(@PathVariable (value="id") long id) {
		//delete event from service
		this.eventService.addEventToCart(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir , Model model) {
		int pageSize = 5;
	
		Page<Event> page = eventService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Event> listEvents = page.getContent();
		
		//model.addAttribute("listEvent", eventService.getAllEvents());
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEvents", listEvents);
		
		return "index"; 
		
	}
}
