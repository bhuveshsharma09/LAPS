package sg.edu.nus.LAPS.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "roleId")
	private Integer roleId; //1

	private String roleTitle; //2 
	
	private String roleDesc; //3 

	//Relations
	@ManyToMany(mappedBy = "roles") // one User can have many roles, and every role can have many Users
	private List<UserCredentials> userCredentials;
}
