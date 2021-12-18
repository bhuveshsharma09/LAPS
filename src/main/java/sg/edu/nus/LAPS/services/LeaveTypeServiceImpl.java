package sg.edu.nus.LAPS.services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.LAPS.model.LeaveType;
import sg.edu.nus.LAPS.repo.LeaveTypeRepository;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

	@Autowired
	LeaveTypeRepository ltrepo;
	
	@Transactional
	public ArrayList<LeaveType> findAllLeaveTypes() {
		return (ArrayList<LeaveType>) ltrepo.findAll();
	}

	@Transactional
	public ArrayList<String> findAllLeaveTypeNames() {
		
		return (ArrayList<String>) ltrepo.findAllLeaveTypeNames();
	}

	@Transactional
	public LeaveType findLeaveTypeByName(String name) {
		return ltrepo.findLeaveTypeByName(name).get(0);
	}

	@Transactional
	public LeaveType createLeaveType(LeaveType leaveType) {
		return ltrepo.saveAndFlush(leaveType);
	}

	@Transactional
	public LeaveType editLeaveType(LeaveType leaveType) {
		return ltrepo.saveAndFlush(leaveType);
	}

	@Transactional
	public void deleteLeaveType(LeaveType leaveType) {
		ltrepo.delete(leaveType);

	}

}
