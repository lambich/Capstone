package ca.sheridancollege.bichl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.bichl.model.CartEvent;
import ca.sheridancollege.bichl.model.User;

@Repository
public interface CartEventRepository extends JpaRepository<CartEvent, Long> {

	public List<CartEvent> findByUser(User user);
}
