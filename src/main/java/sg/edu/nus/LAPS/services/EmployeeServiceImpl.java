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

    
    
    //Added by Melinda on 17 Dec 1.24am
	@Transactional
	public boolean saveEmployee(Employee employee) {
		if(emprepo.save(employee)!=null) return true; else return false;
	}
	
	@Transactional
	public ArrayList<Employee> findAllEmployees() {
		return (ArrayList<Employee>) emprepo.findAll();
	}
	

    
}
