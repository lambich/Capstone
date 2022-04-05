package ca.sheridancollege.bichl.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ca.sheridancollege.bichl.model.Event;
import ca.sheridancollege.bichl.repository.EventRepository;
import ca.sheridancollege.bichl.service.CartEventServices;
import ca.sheridancollege.bichl.service.EventService;
import ca.sheridancollege.bichl.web.filepUpload.FileUploadUtil;

@Controller
public class EventController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private CartEventServices cartEventServices;
	
	@Autowired
	private EventRepository eventRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		return findPaginated(1,"eventName", "asc", model);
	}
	
	//display list of event
	@GetMapping("/showNewEventForm")
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
		return "newEvent";
	}
	 
	@PostMapping("/saveEvent")
	public String saveEvent(@ModelAttribute("event") Event event,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
		//save event to database
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		event.setEventImage(fileName);
		
		Event savedEvent = eventRepository.save(event);
		eventService.saveEvent(event); // original
		
		String uploadDir = "user-photos/" + savedEvent.getId();
		 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
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
