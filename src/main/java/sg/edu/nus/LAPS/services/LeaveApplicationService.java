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
    
    List<LeaveApplication> findPastLeavesByEmployeeId(Integer id);
    
    LeaveApplication deleteLeave(Integer id);
    
    Double getLeaveDuration(Integer id);
    
    Paged<LeaveApplication> findAllLeavesByEmployeeIdWithPage(Integer employeeId, int pageNumber, int size);
    
    List<LeaveApplication> findUpcomingLeavesForEmployee(Integer employeeId, Date inputDate);
    
    List<LeaveApplication> findLeavesByEmployee_employeeIdAndApprovalStatusIn(Integer employeeId, List<ApprovalStatus> approvalStatus);
    
    boolean comapreTwoDates(Date fromDate,Date toDate);

    List<LeaveApplication> findAllLeaveApplicationSorted();

}
