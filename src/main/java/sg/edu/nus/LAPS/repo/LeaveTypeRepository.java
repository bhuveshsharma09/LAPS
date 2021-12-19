package sg.edu.nus.LAPS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.nus.LAPS.model.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType,Integer> {
    @Query(nativeQuery = true,value="Select leave_code From leave_type")
    List<String> findAllLeaveTypes();
}
