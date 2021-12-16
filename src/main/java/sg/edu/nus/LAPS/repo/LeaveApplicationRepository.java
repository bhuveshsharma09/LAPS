package sg.edu.nus.LAPS.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.edu.nus.LAPS.model.LeaveApplication;

import java.util.List;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {

    @Query("SELECT leaves from LeaveApplication leaves where leaves.leaveId >:leaveId")
    List<LeaveApplication> findAllById(@Param("leaveId") int leaveId);

}
