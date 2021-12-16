package sg.edu.nus.LAPS.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.LAPS.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
