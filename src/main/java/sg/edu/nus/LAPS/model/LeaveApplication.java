//There's a problem while creating table of leave, leave history and employee. I (steven) might done something wrong...
//However, all the tables can be created. The problem is there's no FK for LeaveApplication table
package sg.edu.nus.LAPS.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LeaveApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="leaveId")
	private Integer leaveId; //1

	@Temporal(TemporalType.DATE)
	@Column(name = "fromDate")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fromDate; //2

	@Temporal(TemporalType.DATE)
	@Column(name = "toDate")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date toDate; //3


	private String remarks; //4
	private String coveringEmp; //5 
	private String contactDetails; //6 
	@Column(name="approvalStatus",columnDefinition = "ENUM('APPLIED', 'UPDATED', 'CANCELLED', 'APPROVED', 'DELETED', 'REJECTED')")
	@Enumerated(EnumType.STRING)
	private ApprovalStatus approvalStatus; //7

	
	//Relations
	@ManyToOne //Many LeaveApplications can belong to one employee
	@JoinColumn(name = "employeeId")
	private Employee employee;

	@OneToMany(mappedBy = "leaveApplication", cascade = CascadeType.ALL) //One leave application can appear many times in Leave History with different statuses
	private List<LeaveHistory> leaveHistories;

	@ManyToOne //One leave type can have have many leave applications e.g. medical has 10 applications
	@JoinColumn(name="leaveName") 
	private LeaveType leaveType;


	public LeaveApplication(Integer leaveId, Date fromDate, Date toDate, String remarks, String coveringEmp, String contactDetails, ApprovalStatus approvalStatus, Employee employee) {
		this.leaveId = leaveId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.remarks = remarks;
		this.coveringEmp = coveringEmp;
		this.contactDetails = contactDetails;
		this.approvalStatus = approvalStatus;
		this.employee = employee;
	}
}
