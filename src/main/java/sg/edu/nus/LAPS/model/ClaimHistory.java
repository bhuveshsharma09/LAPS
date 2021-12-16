package sg.edu.nus.LAPS.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
public class ClaimHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "claimHistoryId")
	private Integer claimHistoryId; //1

	@Temporal(TemporalType.DATE)
	@Column(name = "timestamp")
	private Date timestamp; //2

	private String changedBy; //3
	private String remarks; //4
	private ApprovalStatus approvalStatus; //5

	
	//Relations
	@ManyToOne //One claim can appear many times in claim history with different statuses
	@JoinColumn(name = "claimId")
	private Claim claim;
}
