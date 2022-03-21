package ca.sheridancollege.bichl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.bichl.model.Event;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
