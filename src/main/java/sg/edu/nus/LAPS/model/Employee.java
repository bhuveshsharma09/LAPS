package sg.edu.nus.LAPS.model;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employeeId")
	private Integer employeeId;

	private String name;
	private String jobTitle;

	// do not need manager_id as the navigation already created department id for us
	// which can be used to get manager id
	//private Integer managerId;

	private Double annualLeaveCount;
	private Double medicalLeaveCount;
	private Double compensationLeaveCount;


	@OneToOne(mappedBy = "employee")
	private UserCredentials userCredentials;


	@OneToMany(mappedBy = "employee")
	private List<LeaveApplication> leaves;

	@ManyToOne
	@JoinColumn(name = "departmentId", referencedColumnName = "departmentId")
	private Department department;

	@OneToMany(mappedBy = "employee")
	private List<Claim> claims;
}
