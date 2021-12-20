package sg.edu.nus.LAPS.services;

import java.util.List;

import sg.edu.nus.LAPS.model.LeaveType;

public interface LeaveTypeService {
    LeaveType saveLeaveType(LeaveType leaveType);
    
    List<Object> findAllLeaveType();
}
