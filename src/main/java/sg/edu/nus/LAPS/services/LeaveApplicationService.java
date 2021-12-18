package sg.edu.nus.LAPS.services;

import java.util.List;

import sg.edu.nus.LAPS.model.LeaveApplication;

public interface LeaveApplicationService {

    List<LeaveApplication> findAllLeaves(Integer eid);

    List<LeaveApplication> findAllById(Integer id);
    
    List<LeaveApplication> findPastLeavesByEmployeeId(Integer id);
}
