package sg.edu.nus.LAPS.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "departmentId")
	private Integer departmentId;

	private String departmentName;

	@NotNull
	private Integer managerId;

	@OneToMany(mappedBy = "department")
	private List<Employee> employees;
}
