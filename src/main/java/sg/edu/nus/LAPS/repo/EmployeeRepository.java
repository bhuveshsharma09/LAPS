package sg.edu.nus.LAPS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.LAPS.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT DISTINCT e2 FROM Employee e1, Employee e2 WHERE e1.employeeId = e2.managerId AND e1.employeeId = :eid")
	public List<Employee> findEmployeesByManagerId(@Param("eid") Integer employeeId);
	
	//added on 17Dec 1.17am while doing Add Employee
	 @Query("Select m from Employee m where m.name LIKE :name")
	 List<Employee> findMemberByFirstName(@Param("name") String name);
	 
	 
	 
	 
}
