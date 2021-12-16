package sg.edu.nus.LAPS.services;

import sg.edu.nus.LAPS.model.LeaveApplication;

import java.util.List;

public interface LeaveApplicationService {



    List<LeaveApplication> findAllById(Integer id);
}
