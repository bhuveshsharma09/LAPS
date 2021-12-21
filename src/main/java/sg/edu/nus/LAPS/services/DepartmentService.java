package sg.edu.nus.LAPS.services;

import java.util.ArrayList;

import sg.edu.nus.LAPS.model.Department;

public interface DepartmentService {

	public ArrayList<Department> findAllDepartments(); //view a list of all departments

	public boolean saveDepartment(Department department); //save a new department

	public Department findById(Integer id); //for edit department
	
	public Department editDepartment(Department department); 

	public void deleteDepartment(Department findById); //delete
}
