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
	private Integer leaveHistoryId; //1

	@Temporal(TemporalType.DATE)
	@Column(name = "timestamp")
	private Date timestamp; //2

	private String changedBy; //3
	private String remarks; //4
	private ApprovalStatus approvalStatus; //5

	//Relations
	@ManyToOne //one leave application can appear many times in leave history with different statuses
	@JoinColumn(name="leaveId")
	private LeaveApplication leaveApplication;
	
}
