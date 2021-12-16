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
	private Integer employeeId; //1

	private String name; //2
	private String jobTitle; //3

	// do not need manager_id as the navigation already created department id for us
	// which can be used to get manager id
	//private Integer managerId;

	private Double annualLeaveCount; //4
	private Double medicalLeaveCount; //5
	private Double compensationLeaveCount; //6

	//Relations
	@OneToOne(mappedBy = "employee") //one Employee can only have one set of login credentials
	private UserCredentials userCredentials;

	@OneToMany(mappedBy = "employee") //one Employee can have many leave applications
	private List<LeaveApplication> leaves;

	@ManyToOne //one Department can have many employees
	@JoinColumn(name = "departmentId", referencedColumnName = "departmentId")
	private Department department;

	@OneToMany(mappedBy = "employee") //one Employee can have many compensation leave claims
	private List<Claim> claims;


	
}
