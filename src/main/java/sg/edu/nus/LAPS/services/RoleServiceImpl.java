package sg.edu.nus.LAPS.services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.LAPS.model.Role;
import sg.edu.nus.LAPS.repo.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository rRepo;
	
	@Transactional
	public ArrayList<String> findAllRolesDesc() {
		return (ArrayList<String>) rRepo.findAllRolesDesc();
	}

	@Transactional
	public Role findRoleByName(String roleDesc) {
		return rRepo.findRoleByName(roleDesc);
	}

	@Transactional
	public ArrayList<Role> findAllRoles() {
		return (ArrayList<Role>) rRepo.findAll();
	}

	@Transactional
	public Role createRole(Role role) {
		return rRepo.saveAndFlush(role);
	}

	@Transactional
	public Role editRole(Role role) {
		return rRepo.saveAndFlush(role);
	}

	@Transactional
	public void deleteRole(Role role) {
		rRepo.delete(role);	
	}

	@Transactional
	public Role findRoleById(Integer id) {
		return rRepo.findRoleById(id);
	}

}
