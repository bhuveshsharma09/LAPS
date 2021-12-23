package sg.edu.nus.LAPS.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.LAPS.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@Query("SELECT DISTINCT d.departmentId FROM Department d")
	List<Integer> findAllById();
	
	@Query("Select d from Department d where d.departmentId = :id")
	Optional<Department> findById(@Param("id") Integer departmentId);
}
