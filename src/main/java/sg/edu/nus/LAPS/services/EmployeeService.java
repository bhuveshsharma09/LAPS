package sg.edu.nus.LAPS.services;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.LAP.model.Employee;
import sg.edu.nus.LAPS.model.LeaveApplication;

public interface EmployeeService {
    
    List<LeaveApplication> findAllLeavesByEid(Integer eid);
    
    //Added by Melinda on 17 Dec 1.23am
    public boolean saveEmployee(Employee employee);
	public ArrayList<Employee> findAllEmployees();

}
