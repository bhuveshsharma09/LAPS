package sg.edu.nus.LAPS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.LAPS.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {


    //Use case: Find all leaves
    @Query("SELECT leaves from LeaveApplication leaves where leaves.leaveId >:leaveId")
    List<LeaveApplication> findAllById(@Param("leaveId") int leaveId);

    //Use case: Find all leaves under the employee
    @Query("SELECT leaves FROM LeaveApplication leaves JOIN leaves.employee e WHERE e.employeeId = :eid")
    List<LeaveApplication> findAllLeaves(@Param("eid") int eid);
    
    //Use case: Find all employees leaves under the manager
    @Query("SELECT leaves FROM LeaveApplication leaves JOIN leaves.employee e WHERE e.managerId = :mid")
    List<LeaveApplication> findAllLeavesOfEmployeeByManagerId(@Param("mid") int mid);

}
