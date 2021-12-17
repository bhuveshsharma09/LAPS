package sg.edu.nus.LAPS.services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
	EmployeeRepository emprepo;
    
    //Added by Melinda on 18 Dec 3.03am
	@Transactional
	public boolean saveEmployee(Employee employee) {
		if(emprepo.save(employee)!=null) 
			return true; 
		else 
			return false;
	}
	
	@Transactional
	public ArrayList<Employee> findAllEmployees() { //find all employees
		return (ArrayList<Employee>) emprepo.findAll();
	}
	
	@Override
	public Employee findEmployeeById(Integer id) { // find by employee ID
		return emprepo.findById(id).get();
	}

	@Override
	public void deleteEmployee(Employee employee) { //delete employee
		emprepo.delete(employee);
	}
	
    
}
