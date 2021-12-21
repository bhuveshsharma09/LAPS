package sg.edu.nus.LAPS.services;

import java.util.ArrayList;

import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.model.LeaveApplication;

public interface EmployeeService {
    
	public ArrayList<Employee> findAllEmployees(); //view a list of all employees
	
    public boolean saveEmployee(Employee employee); //save new or edited employee
    
	public Employee findEmployeeById(Integer id); //edit employee
	
	public Employee editEmployee(Employee e); //edit employee

	public void deleteEmployee(Employee employee); //delete employee
	
	public Employee findEmployeeByName(String name);
	
	public void updateLeaveCount(Employee e, LeaveApplication LA);




    

}
