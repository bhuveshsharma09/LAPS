package sg.edu.nus.LAPS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.repo.LeaveApplicationRepository;

import java.util.List;

@Service
public class LeaveApplicationImpl implements LeaveApplicationService{
    @Autowired
    LeaveApplicationRepository leaveApplicationRepository;




    @Override
    public List<LeaveApplication> findAllById(Integer id) {
        List<LeaveApplication> leaveApplication= leaveApplicationRepository.findAllById(id);
        return leaveApplication;
    }
}
