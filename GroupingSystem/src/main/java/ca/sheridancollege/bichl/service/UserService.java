package ca.sheridancollege.bichl.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ca.sheridancollege.bichl.model.User;
import ca.sheridancollege.bichl.Addition.StudentUserDetails;
import ca.sheridancollege.bichl.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto registrationDto);

	List<User> getAllUsers();

	User saveUser(User student);

	User getUserById(Long id);

	User updateUser(User user);

	void deleteUserById(Long id);

	UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;

	User getCurrentlyLoggedInUser(Authentication authentication);

	User getUserByEmail(String email);

	//public  User getCurrentlyLoggedInUser(Authentication authentication);
	
	 
}
