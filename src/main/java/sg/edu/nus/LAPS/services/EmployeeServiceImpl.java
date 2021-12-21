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
	
	

	@Override
	public Employee findEmployeeByName(String name) {
		return emprepo.findByName(name).get();
	}
	
	
	@Override
	public Employee editEmployee(Employee e) {
		return emprepo.saveAndFlush(e);
	}
	
	//Bowen add

	@Transactional
	public ArrayList<String> findAllEmployeeNames() {
		return (ArrayList<String>) emprepo.findAllEmployeeNames();
	}

	@Transactional
	public Employee findEmployeeByName1(String name) {
		
		return emprepo.findEmployeeByName(name);

	}
	
    
}
