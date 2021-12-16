package sg.edu.nus.LAPS.model;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
public class LeaveHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "leaveHistoryId")
	private Integer leaveHistoryId;

	@Temporal(TemporalType.DATE)
	@Column(name = "timestamp")
	private Date timestamp;

	private String changedBy;
	private String remarks;
	private ApprovalStatus approvalStatus;

	@ManyToOne
	@JoinColumn(name="leaveId")
	private LeaveApplication leaveApplication;
}
