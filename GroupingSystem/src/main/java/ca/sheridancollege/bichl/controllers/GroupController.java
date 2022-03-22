package ca.sheridancollege.bichl.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupController {
	
	@GetMapping("/groups")
	public String groupPage(Model model) {
		return "groupPage.html";
	}

}
