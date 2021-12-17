package sg.edu.nus.LAPS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.LAPS.model.LeaveType;
import sg.edu.nus.LAPS.repo.LeaveTypeRepository;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    @Override
    public LeaveType saveLeaveType(LeaveType leaveType) {
        return leaveTypeRepository.saveAndFlush(leaveType);
        
    }
    
}
