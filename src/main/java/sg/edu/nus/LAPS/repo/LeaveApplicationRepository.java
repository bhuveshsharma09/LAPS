package sg.edu.nus.LAPS.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.LAPS.model.ApprovalStatus;
import sg.edu.nus.LAPS.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {

    //Use case: Find all leaves
    @Query("SELECT leaves from LeaveApplication leaves where leaves.leaveId >:leaveId ")
    List<LeaveApplication> findAllById(@Param("leaveId") int leaveId);

    //Use case: Find all leaves under the employee
    @Query("SELECT leaves FROM LeaveApplication leaves JOIN leaves.employee e WHERE e.employeeId = :eid")
    List<LeaveApplication> findAllLeaves(@Param("eid") int eid);
    
    // Use case: edit leave
    //Find single leave under employee by leaveId
    @Query("SELECT leaves from LeaveApplication leaves where leaves.leaveId = :leaveId")
    LeaveApplication findSingleLeaveById(@Param("leaveId") Integer leaveId);
    
    //Use case: Find all employees leaves under the manager
    @Query("SELECT leaves FROM LeaveApplication leaves JOIN leaves.employee e WHERE e.managerId = :mid AND leaves.approvalStatus = :status")
    List<LeaveApplication> findAllLeavesOfEmployeeByManagerIdWithStatus(@Param("mid") int mid,@Param("status") ApprovalStatus approval);

  //Use case: Find all leaves under the employee at current year
    @Query(value = "SELECT * FROM laps.leave_application WHERE employee_id = :eid AND YEAR(to_date) = :currentYear", nativeQuery = true)
    Page<LeaveApplication> findAllLeaves(@Param("eid") int eid, @Param("currentYear") int year, Pageable page);
    
    //Use case: Find all 'Approved' leaves under the employee at current year
    @Query(value = "SELECT * FROM laps.leave_application WHERE employee_id = :eid AND approval_status = 'APPROVED' AND YEAR(to_date) = :currentYear", nativeQuery = true)
    Page<LeaveApplication> findApprovedLeaves(@Param("eid") int eid, @Param("currentYear") int year, Pageable page);
    
    //Use case: Find all 'Rejected' leaves under the employee at current year
    @Query(value = "SELECT * FROM laps.leave_application WHERE employee_id = :eid AND approval_status = 'REJECTED' AND YEAR(to_date) = :currentYear", nativeQuery = true)
    Page<LeaveApplication> findRejectedLeaves(@Param("eid") int eid, @Param("currentYear") int year, Pageable page);
    
    //Use case: Find all 'Applied' leaves under the employee at current year
    @Query(value = "SELECT * FROM laps.leave_application WHERE employee_id = :eid AND approval_status = 'APPLIED' AND YEAR(to_date) = :currentYear", nativeQuery = true)
    Page<LeaveApplication> findAppliedLeaves(@Param("eid") int eid, @Param("currentYear") int year, Pageable page);
    
    //Use case: Find all 'Updated' leaves under the employee at current year
    @Query(value = "SELECT * FROM laps.leave_application WHERE employee_id = :eid AND approval_status = 'UPDATED' AND YEAR(to_date) = :currentYear", nativeQuery = true)
    Page<LeaveApplication> findUpdatedLeaves(@Param("eid") int eid, @Param("currentYear") int year, Pageable page);
    
    //Use case: Find all 'Cancelled' leaves under the employee at current year
    @Query(value = "SELECT * FROM laps.leave_application WHERE employee_id = :eid AND approval_status = 'CANCELLED' AND YEAR(to_date) = :currentYear", nativeQuery = true)
    Page<LeaveApplication> findCancelledLeaves(@Param("eid") int eid, @Param("currentYear") int year, Pageable page);
    
    List<LeaveApplication> findLeavesByEmployee_employeeIdAndApprovalStatus(Integer employeeId, ApprovalStatus approvalStatus);
    
    List<LeaveApplication> findLeavesByEmployee_employeeIdAndApprovalStatusIn(Integer employeeId, List<ApprovalStatus> approvalStatus);
    
    @Query("select leaves from LeaveApplication leaves where leaves.employee.employeeId = :employeeId "
    		+ "and leaves.approvalStatus = 'APPROVED' "
    		+ "and leaves.fromDate >= :inputDate")
    List<LeaveApplication> findUpcomingLeavesForEmployee(@Param("employeeId") Integer employeeId, @Param("inputDate") Date inputDate);

    @Query("SELECT l FROM LeaveApplication l where l.leaveId = :id")
    LeaveApplication findLeaveApplicationById(@Param("id") Integer id);
    
    // for PDF document download
    @Query(value = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'leave_application'", nativeQuery = true)
    Integer countColumns();
    
    @Query(value = "SELECT datediff(to_date, from_date) FROM laps.leave_application WHERE leave_id = :leaveId", nativeQuery = true)
	Double getLeaveDuration(@Param("leaveId") Integer leaveId);
    
    @Query("SELECT leaves FROM LeaveApplication leaves JOIN leaves.employee e WHERE e.employeeId = :eid")
    Page<LeaveApplication> findAllLeavesByEmployeeIdWithPage(@Param("eid") int eid, Pageable page);

    @Query("SELECT la from LeaveApplication la order by la.leaveId desc")
    List<LeaveApplication> findAllLeaveApplicationSorted();

}
