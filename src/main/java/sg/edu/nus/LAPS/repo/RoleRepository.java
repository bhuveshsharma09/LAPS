package sg.edu.nus.LAPS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.LAPS.model.Role;

public interface RoleRepository extends JpaRepository <Role, Integer>{


   // Role findByRoleId



}
