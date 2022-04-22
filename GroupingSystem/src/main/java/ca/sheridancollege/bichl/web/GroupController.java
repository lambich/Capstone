package ca.sheridancollege.bichl.web;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import ca.sheridancollege.bichl.model.SchoolGroup;
import ca.sheridancollege.bichl.repository.GroupRepository;
import ca.sheridancollege.bichl.repository.UserRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class GroupController {
	
	private GroupRepository groupRepo;
	private UserRepository userRepo;
	
	@GetMapping("/addGroup")
	public String loadAddGroup(Model model) {
		model.addAttribute("group", new SchoolGroup());
		model.addAttribute("users", userRepo.findAll());
		return "addGroup.html";
	}
	
	@Secured("ROLE_USER")
	@PostMapping("/addGroup")
	public String saveGroup(@ModelAttribute("group") SchoolGroup group, Model model, @RequestParam(value="image", required=true) MultipartFile 
			file, @AuthenticationPrincipal Authentication authentication) throws IOException{
		
		Blob blob = null;
	    byte[] blobAsBytes=null;
	    try {
	        blob = new SerialBlob(file.getBytes());
	        
	        int blobLength = (int) blob.length();  
	        blobAsBytes = blob.getBytes(1, blobLength);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    group.setPhoto(blobAsBytes);
	    
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
	    group.setAdmins(userRepo.findByEmail(auth.getName()).getFirstName());
		groupRepo.save(group);
		return "redirect:/";
	}
	
	@GetMapping("/viewGroups")
	public String viewGroups(Model model) {
		model.addAttribute("schoolgroups", groupRepo.findAll());
		return "viewGroups.html";
	}
	
	@GetMapping("/editGroup/{id}")
	public String goEditGroup(@PathVariable Long id, Model model) {
		Optional<SchoolGroup> group = groupRepo.findById(id);
		model.addAttribute("users", userRepo.findAll());
		if(group.isPresent()) {
			SchoolGroup selectedGroup = group.get();
			model.addAttribute("group", selectedGroup);
			return "editGroup.html";
		} else {
			return "redirect:/viewGroups";
		}
	}
	
	@PostMapping("/modifyGroup")
	public String editGroup(@ModelAttribute SchoolGroup group, Model model) {
		groupRepo.save(group);
		return "redirect:/viewGroups";
	}
	
	@GetMapping("/deleteGroup/{id}")
	public String deleteGroup(@PathVariable Long id, Model model) {
		groupRepo.deleteById(id);
		return "redirect:/viewGroups";
	}
	
	@GetMapping("/viewUsers")
	public String viewUsers(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "viewUsers.html";
	}
	
	@GetMapping("/sortById")
	public String sortById(Model model) {
		model.addAttribute("schoolgroups", groupRepo.findByOrderByIdAsc());
		return "viewGroups.html";
	}
	
	@GetMapping("/sortByName")
	public String sortByName(Model model) {
		model.addAttribute("schoolgroups", groupRepo.findByOrderByNameAsc());
		return "viewGroups.html";
	}
	
	@GetMapping("/sortByCategory")
	public String sortByCategory(Model model) {
		model.addAttribute("schoolgroups", groupRepo.findByOrderByCategoryAsc());
		return "viewGroups.html";
	}
	
	@GetMapping("/sortByStudy")
	public String sortByStudy(Model model) {
		model.addAttribute("schoolgroups", groupRepo.findByOrderByStudyAsc());
		return "viewGroups.html";
	}
	
	@GetMapping("/sortByDescription")
	public String sortByDescription(Model model) {
		model.addAttribute("schoolgroups", groupRepo.findByOrderByDescriptionAsc());
		return "viewGroups.html";
	}
	

}
