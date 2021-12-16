package sg.edu.nus.LAPS.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.repo.LeaveApplicationRepository;

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private LeaveApplicationRepository LARepo;

    @Override
    public List<LeaveApplication> findAllLeavesByEid(Integer eid) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
