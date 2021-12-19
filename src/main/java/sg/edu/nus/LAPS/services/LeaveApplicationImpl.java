package sg.edu.nus.LAPS.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<LeaveApplication> findAllLeavesOfEmployeeByManagerIdWithStatus(Integer mid,String status) {
        
        return leaveApplicationRepository.findAllLeavesOfEmployeeByManagerIdWithStatus(mid,status);
    }
    @Override
    public List<LeaveApplication> findPastLeavesByEmployeeId(Integer id) {
        List<LeaveApplication> leaveApplication= leaveApplicationRepository.findPastLeavesByEmployeeId(id);
        return leaveApplication;
    }

    @Override
    public LeaveApplication findLeaveByLeaveId(Integer id) {
        return leaveApplicationRepository.findLeaveApplicationById(id);
    }



}
