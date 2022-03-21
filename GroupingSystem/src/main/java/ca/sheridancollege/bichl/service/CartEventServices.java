package ca.sheridancollege.bichl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.bichl.model.User;
import ca.sheridancollege.bichl.model.CartEvent;
import ca.sheridancollege.bichl.repository.CartEventRepository;

@Service
public class CartEventServices {

		@Autowired
		private CartEventRepository carteventrepository;
		
		public List<CartEvent> listCartEvents(User user){
			return carteventrepository.findByUser(user);
		}
		
		
}
