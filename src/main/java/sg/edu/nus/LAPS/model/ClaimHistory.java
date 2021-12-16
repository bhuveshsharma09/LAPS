package sg.edu.nus.LAPS.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
public class ClaimHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "claimHistoryId")
	private Integer claimHistoryId;

	@Temporal(TemporalType.DATE)
	@Column(name = "timestamp")
	private Date timestamp;

	private String changedBy;
	private String remarks;
	private ApprovalStatus approvalStatus;

	@ManyToOne
	@JoinColumn(name = "claimId")
	private Claim claim;
}
