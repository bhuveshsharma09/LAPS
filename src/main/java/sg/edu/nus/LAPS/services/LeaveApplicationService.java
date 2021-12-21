package sg.edu.nus.LAPS.services;

import java.util.Date;
import java.util.List;

import sg.edu.nus.LAPS.model.ApprovalStatus;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.model.paging.Paged;

public interface LeaveApplicationService {

    List<LeaveApplication> findAllLeaves(Integer eid);

    List<LeaveApplication> findAllById(Integer id);
    
    LeaveApplication findSingleLeaveById(Integer leaveId);

    LeaveApplication saveLeaveApplication(LeaveApplication LA);

    List<LeaveApplication> findAllLeavesOfEmployeeByManagerIdWithStatus(Integer mid,ApprovalStatus status);
    
    Paged<LeaveApplication> findAllLeaves(Integer eid, Integer Year, int pageNumber, int size);
    
    Paged<LeaveApplication> findApprovedLeaves(Integer id, Integer Year, int pageNumber, int size);
    
    Paged<LeaveApplication> findRejectedLeaves(Integer id, Integer Year, int pageNumber, int size);
    
    Paged<LeaveApplication> findAppliedLeaves(Integer id, Integer Year, int pageNumber, int size);
    
    Paged<LeaveApplication> findUpdatedLeaves(Integer id, Integer Year, int pageNumber, int size);
    
    Paged<LeaveApplication> findCancelledLeaves(Integer id, Integer Year, int pageNumber, int size);
    
    LeaveApplication deleteLeave(Integer id);
    
    Paged<LeaveApplication> findAllLeavesByEmployeeIdWithPage(Integer employeeId, int pageNumber, int size);
    
    List<LeaveApplication> findUpcomingLeavesForEmployee(Integer employeeId, Date inputDate);
    
    List<LeaveApplication> findLeavesByEmployee_employeeIdAndApprovalStatusIn(Integer employeeId, List<ApprovalStatus> approvalStatus);
}
