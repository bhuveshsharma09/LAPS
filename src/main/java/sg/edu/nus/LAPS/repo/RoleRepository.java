package sg.edu.nus.LAPS.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.LAPS.model.Role;

public interface RoleRepository extends JpaRepository <Role, Integer>{

	@Query("SELECT r.roleDesc FROM Role r")
	ArrayList<String> findAllRolesDesc();
	
	@Query("SELECT r FROM Role r WHERE r.roleDesc = :roleDesc")
	Role findRoleByName(@Param("roleDesc") String roleDesc);

	@Query("SELECT r FROM Role r WHERE r.roleId = :roleId")
	Role findRoleById(@Param("roleId") Integer id);

}
