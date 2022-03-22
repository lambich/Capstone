package ca.sheridancollege.bichl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.bichl.beans.CartEvent;
import ca.sheridancollege.bichl.beans.User;

@Repository
public interface CartEventRepository extends JpaRepository<CartEvent, Long> {

	public List<CartEvent> findByUser(User user);
}
