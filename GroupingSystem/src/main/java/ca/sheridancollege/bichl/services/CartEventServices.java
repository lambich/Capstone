package ca.sheridancollege.bichl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.bichl.beans.CartEvent;
import ca.sheridancollege.bichl.beans.User;
import ca.sheridancollege.bichl.repositories.CartEventRepository;

@Service
public class CartEventServices {

		@Autowired
		private CartEventRepository carteventrepository;
		
		public List<CartEvent> listCartEvents(User user){
			return carteventrepository.findByUser(user);
		}
		
		
}
