package ca.sheridancollege.bichl.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "host_name")
	private String hostName;
	
	@Column(name = "event_name")
	private String eventName;
	
	@Column(name = "description")
	private String Description;
	
	@Column(name = "type_of_event")
	private String typeOfEvent;
	
	@Column(name = "category")
	private String Category;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "time")
	private Time time;
	
	@Column(name = "num_of_spot")
	private int numOfSpot;
	
	@Column(name = "num_of_attendance")
	private int numOfAttentdance;
	
	public Event()
	{
		
	}
	
	public Event(String hostName, String eventName, String description, String typeOfEvent, String category,
			String location, Date date, Time time, int numOfSpot, int numOfAttentdance) {
		super();
		this.hostName = hostName;
		this.eventName = eventName;
		Description = description;
		this.typeOfEvent = typeOfEvent;
		Category = category;
		this.location = location;
		this.date = date;
		this.time = time;
		this.numOfSpot = numOfSpot;
		this.numOfAttentdance = numOfAttentdance;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getTypeOfEvent() {
		return typeOfEvent;
	}
	public void setTypeOfEvent(String typeOfEvent) {
		this.typeOfEvent = typeOfEvent;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public int getNumOfSpot() {
		return numOfSpot;
	}
	public void setNumOfSpot(int numOfSpot) {
		this.numOfSpot = numOfSpot;
	}
	public int getNumOfAttentdance() {
		return numOfAttentdance;
	}
	public void setNumOfAttentdance(int numOfAttentdance) {
		this.numOfAttentdance = numOfAttentdance;
	}
		
}
