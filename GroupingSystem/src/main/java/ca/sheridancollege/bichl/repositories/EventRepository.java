package ca.sheridancollege.bichl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.bichl.beans.Event;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
