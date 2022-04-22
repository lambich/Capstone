package ca.sheridancollege.bichl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.bichl.model.Event;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	
	@Query(value="SELECT * FROM events ORDER BY num_of_attendance DESC LIMIT 2",nativeQuery=true)
	List<Event> getTwoEvents();
	
	@Query(value="SELECT * FROM events WHERE host_name like %:name% ORDER BY num_of_attendance DESC LIMIT 2",nativeQuery=true)
	List<Event> getUserEvents(@Param("name")String name);
}