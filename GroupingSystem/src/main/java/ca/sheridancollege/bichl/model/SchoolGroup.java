package ca.sheridancollege.bichl.model;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class SchoolGroup {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String name;
	@NonNull
	private String category;
	@NonNull
	private String study;

	private String admins;
	private String invites;
	@NonNull
	private String description;
	private Boolean chat;

	@Lob
	@Column(name = "photo", nullable = true)
	private byte[] photo;
	
	@Transient
	String base64Encoded;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="groups")
	private List<User> users;
}
