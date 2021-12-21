package sg.edu.nus.LAPS.repo;


import java.util.List;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import sg.edu.nus.LAPS.model.LeaveType;




import org.springframework.data.repository.query.Param;

import sg.edu.nus.LAPS.model.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, String> {

	@Query("SELECT l.leaveName FROM LeaveType l")
	ArrayList<String> findAllLeaveTypeNames();
	
	@Query("SELECT l FROM LeaveType l WHERE l.leaveName = :name")
	ArrayList<LeaveType> findLeaveTypeByName(@Param("name") String name);
	
	@Query(nativeQuery = true,value="Select lt.leave_name,lt.leave_code From leave_type lt")
    List<Object> findAllLeaveTypes();

}
