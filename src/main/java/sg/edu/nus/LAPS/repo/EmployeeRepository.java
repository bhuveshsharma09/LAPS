package sg.edu.nus.LAPS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.LAPS.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	public List<Employee> findEmployeesByManager_EmployeeId(Integer employeeId);
	
	//added on 17Dec 1.17am while doing Add Employee
	@Query("Select m from Employee m where m.name LIKE :name")
	List<Employee> findMemberByFirstName(@Param("name") String name);

	@Query("Select e from Employee e where e.employeeId = :id")
	Employee findByEmployeeId(@Param("id") Integer employeeId);
}
