package sg.edu.nus.LAPS.services;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sg.edu.nus.LAPS.model.ApprovalStatus;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.model.paging.Paged;
import sg.edu.nus.LAPS.model.paging.Paging;
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
    
    @Override
    public Paged<LeaveApplication> findAllLeavesByEmployeeIdWithPage(Integer employeeId, int pageNumber, int size) {
    	PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<LeaveApplication> historicalLeaves = leaveApplicationRepository.findAllLeavesByEmployeeIdWithPage(employeeId, request);
        return new Paged<>(historicalLeaves, Paging.of(historicalLeaves.getTotalPages(), pageNumber, size));
    }
    
    @Override
    public List<LeaveApplication> findUpcomingLeavesForEmployee(Integer employeeId, Date inputDate) {
    	return leaveApplicationRepository.findUpcomingLeavesForEmployee(employeeId, inputDate);
    }
    
    @Override
    public List<LeaveApplication> findLeavesByEmployee_employeeIdAndApprovalStatusIn(Integer employeeId, List<ApprovalStatus> approvalStatus) {
    	return leaveApplicationRepository.findLeavesByEmployee_employeeIdAndApprovalStatusIn(employeeId, approvalStatus);
    }

    @Override
    public boolean comapreTwoDates(Date fromDate, Date toDate){
        if(fromDate.compareTo(toDate)>0){
            return false;
        }else{
            return true;
        }
    }
    @Override
    public List<LeaveApplication> findAllLeaveApplicationSorted(){
        return leaveApplicationRepository.findAllLeaveApplicationSorted();
    }
    
    @Override
    public Paged<LeaveApplication> findApprovedLeaves(Integer id, Integer year, int pageNumber, int size) {
    	PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<LeaveApplication> leaveApplication= leaveApplicationRepository.findApprovedLeaves(id, year, request);
        return new Paged<>(leaveApplication, Paging.of(leaveApplication.getTotalPages(), pageNumber, size));
    }
    
    @Override
    public Paged<LeaveApplication> findRejectedLeaves(Integer id, Integer year, int pageNumber, int size) {
    	PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<LeaveApplication> leaveApplication= leaveApplicationRepository.findRejectedLeaves(id, year, request);
        return new Paged<>(leaveApplication, Paging.of(leaveApplication.getTotalPages(), pageNumber, size));
    }
    
    @Override
    public Paged<LeaveApplication> findAppliedLeaves(Integer id, Integer year, int pageNumber, int size) {
    	PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<LeaveApplication> leaveApplication= leaveApplicationRepository.findAppliedLeaves(id, year, request);
        return new Paged<>(leaveApplication, Paging.of(leaveApplication.getTotalPages(), pageNumber, size));
    }
    
    @Override
    public Paged<LeaveApplication> findUpdatedLeaves(Integer id, Integer year, int pageNumber, int size) {
    	PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<LeaveApplication> leaveApplication= leaveApplicationRepository.findUpdatedLeaves(id, year, request);
        return new Paged<>(leaveApplication, Paging.of(leaveApplication.getTotalPages(), pageNumber, size));
    }
    
    @Override
    public Paged<LeaveApplication> findCancelledLeaves(Integer id, Integer year, int pageNumber, int size) {
    	PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<LeaveApplication> leaveApplication= leaveApplicationRepository.findCancelledLeaves(id, year, request);
        return new Paged<>(leaveApplication, Paging.of(leaveApplication.getTotalPages(), pageNumber, size));
    }
    
    @Override
    public Paged<LeaveApplication> findAllLeaves(Integer eid, Integer year, int pageNumber, int size) {
    	PageRequest request = PageRequest.of(pageNumber - 1, size);
    	Page<LeaveApplication> leaveApplication= leaveApplicationRepository.findAllLeaves(eid, year, request);
        return new Paged<>(leaveApplication, Paging.of(leaveApplication.getTotalPages(), pageNumber, size));
    }
}
