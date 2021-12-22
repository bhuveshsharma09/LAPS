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

	private Double annualLeaveCount; //3
	private Double medicalLeaveCount; //4
	private Double compensationLeaveCount; //5

	@Column(name = "emailId")
	private String emailId; //6
	
	private Integer managerId;
	

	//Relations
	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL) //one Employee can only have one set of login credentials
	private UserCredentials userCredentials;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL) //one Employee can have many leave applications
	private List<LeaveApplication> leaves;

	@ManyToOne //one Department can have many employees
	@JoinColumn(name = "departmentId", referencedColumnName = "departmentId")
	private Department department;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL) //one Employee can have many compensation leave claims
	private List<Claim> claims;
	
	@ManyToOne
	private Employee manager; 

	@Override
	public String toString() {
		return employeeId + "- " + name;
	}
	


	
}
