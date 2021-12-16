package sg.edu.nus.LAPS.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.LAPS.model.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {

}
