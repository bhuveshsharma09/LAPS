package sg.edu.nus.LAPS.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_credential")
public class UserCredentials {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	private int userId;

	@NonNull
	private String username;

	@NotNull
	private String password;

	private Integer employeeId;

	@OneToOne
	private Employee employee;

	@ManyToMany
	private List<Role> roles;
}
