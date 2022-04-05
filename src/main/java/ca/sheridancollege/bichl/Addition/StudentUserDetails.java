package ca.sheridancollege.bichl.Addition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ca.sheridancollege.bichl.model.Role;
import ca.sheridancollege.bichl.model.User;

public class StudentUserDetails implements UserDetails {

	private User user;
	
	public StudentUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = (Set<Role>) user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	public String getEmail() {
		return user.getEmail();
	}
	
	public User getUser() {
		return this.user;
	}
	

	@Override
	public String getUsername() {
		return user.getFirstName() + " " + user.getLastName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
