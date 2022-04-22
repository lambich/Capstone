package ca.sheridancollege.bichl.web;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.bichl.model.Event;
import ca.sheridancollege.bichl.model.Role;
import ca.sheridancollege.bichl.model.SchoolGroup;
import ca.sheridancollege.bichl.model.User;
import ca.sheridancollege.bichl.repository.EventRepository;
import ca.sheridancollege.bichl.repository.GroupRepository;
import ca.sheridancollege.bichl.repository.UserRepository;
import ca.sheridancollege.bichl.service.UserService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private EventRepository eventRepo;
	private GroupRepository groupRepo;
	private UserRepository userRepo;
	
	@GetMapping("/")
	public String goHome(Model model, @AuthenticationPrincipal Authentication authentication) throws 
	UnsupportedEncodingException {
		
	//-------------------------------------------Authentication
	Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
	User user = userRepo.findByEmail(auth.getName());
	
	if(user!=null) {
		model.addAttribute("user",user.getFirstName());
		
		//--------------------Top two events
				List<Event> events=eventRepo.getUserEvents(user.getFirstName());
				
				for(int i=0;i<events.size();i++) {
					byte[] encodeBase64 = Base64.getEncoder().encode(events.get(i).getEventImage());
					String base64Encoded = new String(encodeBase64, "UTF-8");
					events.get(i).setBase64Encoded(base64Encoded);
				}

				model.addAttribute("events",events);
				
		//--------------------Top two groups
		List<SchoolGroup> groups=groupRepo.getUserGroup(user.getFirstName());
		
		for(int i=0;i<groups.size();i++) {
			byte[] encodeBase64 = Base64.getEncoder().encode(groups.get(i).getPhoto());
			String base64Encoded = new String(encodeBase64, "UTF-8");
			groups.get(i).setBase64Encoded(base64Encoded);
		}

		model.addAttribute("groups",groups);
	}else {
		//--------------------Top two events
		List<Event> events=eventRepo.getTwoEvents();
		
		for(int i=0;i<events.size();i++) {
			byte[] encodeBase64 = Base64.getEncoder().encode(events.get(i).getEventImage());
			String base64Encoded = new String(encodeBase64, "UTF-8");
			events.get(i).setBase64Encoded(base64Encoded);
		}

		model.addAttribute("events",events);
		
		//--------------------Top two groups
		List<SchoolGroup> groups=groupRepo.getTwoGroups();
		
		for(int i=0;i<groups.size();i++) {
			byte[] encodeBase64 = Base64.getEncoder().encode(groups.get(i).getPhoto());
			String base64Encoded = new String(encodeBase64, "UTF-8");
			groups.get(i).setBase64Encoded(base64Encoded);
		}

		model.addAttribute("groups",groups);
	}

	
	return "home.html";
}
}