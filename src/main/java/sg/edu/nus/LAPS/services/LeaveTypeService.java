package sg.edu.nus.LAPS.services;


import java.util.List;

import sg.edu.nus.LAPS.model.LeaveType;




import java.util.ArrayList;

import sg.edu.nus.LAPS.model.LeaveType;

public interface LeaveTypeService {
	public ArrayList<LeaveType> findAllLeaveTypes();
	
	public ArrayList<String> findAllLeaveTypeNames();
	
	public LeaveType findLeaveTypeByName(String name);
	
	public LeaveType createLeaveType(LeaveType leaveType);
	
	public LeaveType editLeaveType(LeaveType leaveType);
	
	public void deleteLeaveType(LeaveType leaveType);
	
    LeaveType saveLeaveType(LeaveType leaveType);
    
    List<Object> findAllLeaveType();
	
	

}
