package sg.edu.nus.LAPS.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

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
	
	@NotEmpty(message = "Role Title cannot be empty")
	@Length(max=15)
	private String roleTitle; //2 
	
	@NotEmpty(message = "Role Description cannot be empty")
	@Length(max=15)
	private String roleDesc; //3 

	//Relations
	@OneToMany(mappedBy = "roles") // one User can have many roles, and every role can have many Users
	private List<UserCredentials> userCredentials;
}
