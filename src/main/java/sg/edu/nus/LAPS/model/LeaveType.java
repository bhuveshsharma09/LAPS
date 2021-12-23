package sg.edu.nus.LAPS.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LeaveType {

	//@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name = "leaveTypeId")
	//private Integer leaveTypeId; //1

	@Id
	@Column(name = "leaveName")
	@NotEmpty(message = "Leave Name cannot be empty")
	private String leaveName; //2

	@Column(name = "granularity")
	@NotEmpty(message = "Granularity cannot be empty")
	private Integer granularity; //3
	
	@Column(name = "leaveCode")
	@NotEmpty(message = "Leave Code cannot be empty")
	private String leaveCode; //4 - ML, AL, CL

	//Relations
	@OneToMany(mappedBy = "leaveType", cascade = CascadeType.ALL) //one leave type can have many leave applications under it
	private List<LeaveApplication> leaves;
}
