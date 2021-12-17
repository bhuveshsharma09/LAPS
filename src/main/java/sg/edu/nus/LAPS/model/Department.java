package sg.edu.nus.LAPS.model;

import java.util.List;

import javax.persistence.CascadeType;
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
	private Integer departmentId; //1

	private String departmentName; //2

	@NotNull
	private Integer managerId; //3

	//Relations
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL) //one Department can have many Employees
	private List<Employee> employees;
	
}
