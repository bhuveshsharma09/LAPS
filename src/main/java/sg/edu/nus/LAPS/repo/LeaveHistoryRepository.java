package sg.edu.nus.LAPS.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.model.LeaveHistory;

import java.util.List;

public interface LeaveHistoryRepository extends JpaRepository<LeaveHistory, Integer> {




}
