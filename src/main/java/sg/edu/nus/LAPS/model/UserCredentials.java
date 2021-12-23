package sg.edu.nus.LAPS.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_credential")
public class UserCredentials {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	private Integer userId; //1

	@NonNull
	@Length(max=20)
	@Column(name="username")
	private String username;//2

	@NotNull
	@Length(max=20)
	@Column(name="password")
	private String password;//3

	private Integer employeeId; //4

	//Relations
	@OneToOne//One employee can only have one login account
	private Employee employee;

	@ManyToMany //One User can have many roles, and every role can have many Users
	private List<Role> roles;

	public UserCredentials(Integer userId, @NonNull String username, String password) {
		this.userId = userId;
		this.username = username;
		this.password = password;
	}


	
}
