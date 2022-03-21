package ca.sheridancollege.bichl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import ca.sheridancollege.bichl.model.CartEvent;
import ca.sheridancollege.bichl.model.Event;
import ca.sheridancollege.bichl.model.User;
import ca.sheridancollege.bichl.repository.CartEventRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SelectingEventTests {

	@Autowired
	private CartEventRepository carteventrepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	Long i= (long) 8;
	Long ii= (long) 2;
	
	@Test
	public void TestAddOneCartEvent() {
		Event event = entityManager.find(Event.class, i);
		User user = entityManager.find(User.class, ii);
		
		CartEvent newEvent = new CartEvent();
		newEvent.setUser(user);
		newEvent.setEvent(event);
		newEvent.setQuantity(1);
		
		CartEvent saveCartEvent = carteventrepository.save(newEvent);
		
		assertTrue(saveCartEvent.getId() > 0);
	}
	
	@Test
	public void testGetCartEventByUser() {
		User user = new User();
		user.setId((long) 4);
		
		List<CartEvent> cartevent = carteventrepository.findByUser(user);
		
		assertEquals(1, cartevent.size());
	}

}
