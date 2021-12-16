package sg.edu.nus.LAPS.model;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LeaveType {

	@Id
	/*@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "leaveTypeId")
	private Integer leaveTypeId;*/
	@Column(name = "leaveName")
	private String leaveName;

	private Integer granularity;
	private String leaveCode;

	@OneToMany(mappedBy = "leaveType")
	private List<LeaveApplication> leaves;
}
