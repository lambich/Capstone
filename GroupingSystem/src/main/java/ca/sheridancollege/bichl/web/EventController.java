package ca.sheridancollege.bichl.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.bichl.model.Event;
import ca.sheridancollege.bichl.service.EventService;

@Controller
public class EventController {

	@Autowired
	private EventService eventService;
	
	@GetMapping("/")
	public String home(Model model) {
		return findPaginated(1,"eventName", "asc", model);
	}
	
	//display list of event
	@GetMapping("/showNewEventForm")
	public String showNewEventForm(Model model) {
		//Create model attribute to bind from data
		List<String> typeOfEventList = Arrays.asList("Online","In Person (Indoor)","In Person (Outdoor)");
		model.addAttribute("typeOfEventList",typeOfEventList);
		
		Event event = new Event();
		model.addAttribute("event", event);
		return "newEvent";
	}
	 
	@PostMapping("/saveEvent")
	public String saveEvent(@ModelAttribute("event") Event event) {
		//save event to database
		eventService.saveEvent(event);
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
		this.eventService.deleteEventById(id);
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
