package sg.edu.nus.LAPS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.nus.LAPS.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@Query("SELECT DISTINCT d.departmentId FROM Department d")
	List<Integer> findAllById();
}
