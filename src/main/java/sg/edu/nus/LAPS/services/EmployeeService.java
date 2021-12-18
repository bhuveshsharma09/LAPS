package sg.edu.nus.LAPS.services;

import java.util.ArrayList;

import sg.edu.nus.LAPS.model.Employee;

public interface EmployeeService {
    
    //Added by Melinda on 18 Dec 3.02am
	public ArrayList<Employee> findAllEmployees(); //view a list of all employees
	
    public boolean saveEmployee(Employee employee); //save new or edited employee
    
	public Employee findEmployeeById(Integer id); //edit employee

	public void deleteEmployee(Employee employee); //delete employee

    

}
