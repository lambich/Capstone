package ca.sheridancollege.bichl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ca.sheridancollege.bichl.Addition.StudentUserDetails;
import ca.sheridancollege.bichl.beans.User;
import ca.sheridancollege.bichl.repositories.UserRepository;

public class StudentUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userrepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userrepository.findByEmail(email);
		if(user == null)
			throw new UsernameNotFoundException("User not found");
		return new StudentUserDetails(user);
	}

}
