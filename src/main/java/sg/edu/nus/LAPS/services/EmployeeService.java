package sg.edu.nus.LAPS.services;

import java.util.List;

import sg.edu.nus.LAPS.model.LeaveApplication;

public interface EmployeeService {
    
    List<LeaveApplication> findAllLeavesByEid(Integer eid);
}
