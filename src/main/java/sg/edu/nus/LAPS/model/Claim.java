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
	private Integer claimId;

	@Temporal(TemporalType.DATE)
	@Column(name = "overtimeDate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date overtimeDate;

	private Double hoursWorked;
	private Double eligibleClaim;
	private String remarks;
	private ApprovalStatus approvalStatus;

	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;

	@OneToMany(mappedBy = "claim")
	private List<ClaimHistory> claimHistories;
}
