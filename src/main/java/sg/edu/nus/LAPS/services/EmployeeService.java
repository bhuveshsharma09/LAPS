package sg.edu.nus.LAPS.services;

import java.util.ArrayList;

import sg.edu.nus.LAPS.model.Employee;

public interface EmployeeService {
    
    
    
    //Added by Melinda on 17 Dec 1.23am
    public boolean saveEmployee(Employee employee);
	public ArrayList<Employee> findAllEmployees();

    public Employee findEmployeeById(Integer id);
}
