package sg.edu.nus.LAPS.services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
	EmployeeRepository emprepo;
    
    @Autowired
    LeaveApplicationService leaveApplicationService;
    
   
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


	@Override
	public void updateLeaveCount(Employee e, LeaveApplication LA) {
		
		// get leave duration and leave type
		Double leaveDuration = leaveApplicationService.getLeaveDuration(LA.getLeaveId());
		String leaveType = LA.getLeaveType().getLeaveName();
		String approvalStatus = LA.getApprovalStatus().toString();
		
		// if leave was approved, subtract leave duration from employee's leave count
		if (approvalStatus.equalsIgnoreCase("APPROVED"))
		{
			if (leaveType.equalsIgnoreCase("AL")) {
				e.setAnnualLeaveCount(e.getAnnualLeaveCount() - leaveDuration);
			}
			else if (leaveType.equalsIgnoreCase("ML")) {
				e.setMedicalLeaveCount(e.getMedicalLeaveCount() - leaveDuration);
			}
		}
		
		// if leave was cancelled, return subtracted leave days to leave count
		else if (approvalStatus.equalsIgnoreCase("CANCELLED")) {
			if (leaveType.equalsIgnoreCase("AL")) {
				e.setAnnualLeaveCount(e.getAnnualLeaveCount() + leaveDuration);
			}
			else if (leaveType.equalsIgnoreCase("ML")) {
				e.setMedicalLeaveCount(e.getMedicalLeaveCount() + leaveDuration);
			}
		}	
		
		// save changes
		emprepo.saveAndFlush(e);
		
	}
	
    
}
