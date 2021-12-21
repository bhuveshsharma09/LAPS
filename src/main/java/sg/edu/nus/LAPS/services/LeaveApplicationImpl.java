package sg.edu.nus.LAPS.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.LAPS.model.ApprovalStatus;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.repo.LeaveApplicationRepository;

@Service
public class LeaveApplicationImpl implements LeaveApplicationService{
    @Autowired
    LeaveApplicationRepository leaveApplicationRepository;


    @Override
    public List<LeaveApplication> findAllLeaves(Integer eid) {
        return leaveApplicationRepository.findAllLeaves(eid);
    }

    @Override
    public List<LeaveApplication> findAllById(Integer id) {
        List<LeaveApplication> leaveApplication= leaveApplicationRepository.findAllById(id);
        return leaveApplication;
    }

    @Override
    public LeaveApplication saveLeaveApplication(LeaveApplication LA) {
        return leaveApplicationRepository.saveAndFlush(LA);
    }

    @Override
    public List<LeaveApplication> findAllLeavesOfEmployeeByManagerIdWithStatus(Integer mid,ApprovalStatus status) {
        
        return leaveApplicationRepository.findAllLeavesOfEmployeeByManagerIdWithStatus(mid,status);
    }
    @Override
    public List<LeaveApplication> findPastLeavesByEmployeeId(Integer id) {
        List<LeaveApplication> leaveApplication= leaveApplicationRepository.findPastLeavesByEmployeeId(id);
        return leaveApplication;
    }
    
    @Override
    public LeaveApplication findSingleLeaveById(Integer leaveId) {
        LeaveApplication leaveApplication = leaveApplicationRepository.findSingleLeaveById(leaveId);
        return leaveApplication;
    }
    
    @Override
	public LeaveApplication deleteLeave(Integer id) {
		leaveApplicationRepository.deleteById(id);
		return null;
	}

	@Override
	public Double getLeaveDuration(Integer id) {
		return leaveApplicationRepository.getLeaveDuration(id);
		
	}
}
