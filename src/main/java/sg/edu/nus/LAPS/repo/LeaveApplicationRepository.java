package sg.edu.nus.LAPS.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.LAPS.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {

}
