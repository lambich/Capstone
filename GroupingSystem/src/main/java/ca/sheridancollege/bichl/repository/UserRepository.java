package ca.sheridancollege.bichl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ca.sheridancollege.bichl.model.User;


public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value="SELECT * FROM users WHERE email LIKE %:email%",nativeQuery=true)
	User findByEmail(@Param("email")String email);
}