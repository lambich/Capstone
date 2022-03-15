package ca.sheridancollege.bichl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.bichl.model.User;


public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
}