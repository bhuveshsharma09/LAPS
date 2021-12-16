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
public class Claim {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "claimId")
	private Integer claimId; //1

	@Temporal(TemporalType.DATE)
	@Column(name = "overtimeDate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date overtimeDate; //2

	private Double hoursWorked; //3
	private Double eligibleClaim; //4
	private String remarks; //5
	private ApprovalStatus approvalStatus; //6

	
	//Relations
	@ManyToOne //Many claims can belong to the same employee
	@JoinColumn(name = "employeeId")
	private Employee employee;

	@OneToMany(mappedBy = "claim") //one claim can appear many times under claim history with different statuses
	private List<ClaimHistory> claimHistories;
}
