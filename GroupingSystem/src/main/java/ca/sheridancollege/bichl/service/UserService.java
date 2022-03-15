package ca.sheridancollege.bichl.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ca.sheridancollege.bichl.model.User;
import ca.sheridancollege.bichl.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{

	User save(UserRegistrationDto registrationDto);
	
	List<User> getAllUsers();
	
	User saveUser(User student);
	
	User getUserById(Long id);
	
	User updateUser(User user);
	
	void deleteUserById(Long id);
}
