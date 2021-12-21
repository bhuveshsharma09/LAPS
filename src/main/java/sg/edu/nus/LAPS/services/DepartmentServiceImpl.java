package sg.edu.nus.LAPS.services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.LAPS.model.Department;
import sg.edu.nus.LAPS.repo.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	DepartmentRepository drepo;
	

	@Transactional
	public ArrayList<Department> findAllDepartments() {
		return (ArrayList<Department>) drepo.findAll();
	}
	

	@Transactional
	public boolean saveDepartment(Department department) {
		if(drepo.save(department)!=null) 
			return true; 
		else 
			return false;
	}
	

	@Transactional
	public Department findById(Integer id) {
		return drepo.findById(id).get();
	}

	
	@Transactional
	public Department editDepartment(Department department) {
		return drepo.saveAndFlush(department);
	}
	

	@Transactional
	public void deleteDepartment(Department d) {
		drepo.delete(d);
	}



}
