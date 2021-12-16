package sg.edu.nus.LAPS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.LAPS.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	public List<Employee> findEmployeesByManager_EmployeeId(Integer employeeId);
}
