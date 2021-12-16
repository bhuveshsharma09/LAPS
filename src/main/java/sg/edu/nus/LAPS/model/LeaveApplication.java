//There's a problem while creating table of leave, leave history and employee. I (steven) might done something wrong...
//However, all the tables can be created. The problem is there's no FK for LeaveApplication table
package sg.edu.nus.LAPS.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@NoArgsConstructor
public class LeaveApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="leaveId")
	private int leaveId;

	@Temporal(TemporalType.DATE)
	@Column(name = "fromDate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fromDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "toDate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date toDate;


	private String remarks;
	private String coveringEmp;
	private String contactDetails;
	private ApprovalStatus approvalStatus;

	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;

	@OneToMany(mappedBy = "leaveApplication")
	private List<LeaveHistory> leaveHistories;

	@ManyToOne
	@JoinColumn(name="leaveName")
	private LeaveType leaveType;
}
