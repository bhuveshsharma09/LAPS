package sg.edu.nus.LAPS.model;

import java.util.List;

import javax.persistence.*;

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
	private String leaveName; //2

	@Column(name = "granularity")
	private Integer granularity; //3
	
	@Column(name = "leaveCode")
	private String leaveCode; //4 - ML, AL, CL

	//Relations
	@OneToMany(mappedBy = "leaveType") //one leave type can have many leave applications under it
	private List<LeaveApplication> leaves;
}
