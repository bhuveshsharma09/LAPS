package sg.edu.nus.LAPS.services;

import java.util.ArrayList;

import sg.edu.nus.LAPS.model.Role;

public interface RoleService {
	
	ArrayList<Role> findAllRoles();
	
	ArrayList<String> findAllRolesDesc();
	
	Role findRoleByName(String roleDesc);
	
	Role findRoleById(Integer id);
	
	public Role createRole(Role role);
	
	public Role editRole(Role role);
	
	public void deleteRole(Role role);

}
