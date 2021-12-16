package sg.edu.nus.LAPS.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.repo.EmployeeRepository;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.repo.LeaveApplicationRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private LeaveApplicationRepository LARepo;
    
    @Autowired
	EmployeeRepository emprepo;

    @Override
    public List<LeaveApplication> findAllLeavesByEid(Integer eid) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
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
