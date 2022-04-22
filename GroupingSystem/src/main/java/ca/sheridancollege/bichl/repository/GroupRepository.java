package ca.sheridancollege.bichl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.bichl.model.Event;
import ca.sheridancollege.bichl.model.SchoolGroup;


@Repository
public interface GroupRepository extends JpaRepository<SchoolGroup, Long> {

	public List<SchoolGroup> findByOrderByIdAsc();
	
	public List<SchoolGroup> findByOrderByNameAsc();
	
	public List<SchoolGroup> findByOrderByCategoryAsc();
	
	public List<SchoolGroup> findByOrderByStudyAsc();
	
	public List<SchoolGroup> findByOrderByDescriptionAsc();
	
	@Query(value="SELECT * FROM school_group LIMIT 2",nativeQuery=true)
	List<SchoolGroup> getTwoGroups();
	
	@Query(value="SELECT * FROM school_group WHERE admins like %:name% LIMIT 2",nativeQuery=true)
	List<SchoolGroup> getUserGroup(@Param("name")String name);
}