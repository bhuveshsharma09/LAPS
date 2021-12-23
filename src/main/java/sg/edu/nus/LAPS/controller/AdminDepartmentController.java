package sg.edu.nus.LAPS.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.LAPS.model.Department;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.services.DepartmentService;
import sg.edu.nus.LAPS.services.DepartmentServiceImpl;
import sg.edu.nus.LAPS.services.EmployeeService;
import sg.edu.nus.LAPS.services.EmployeeServiceImpl;

@Controller
@RequestMapping("/department") //URL
public class AdminDepartmentController {
	
	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	public void setDepartmentService(DepartmentServiceImpl deptServiceImpl) {
		this.deptService = deptServiceImpl;
	}
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	public void setEmployeeService(EmployeeServiceImpl empServiceImpl) {
		this.employeeService = empServiceImpl;
	}
	
	//View a list of all departments - department/all
	@RequestMapping(value = "/all") 
	public String list(Model model) {
		model.addAttribute("departments", deptService.findAllDepartments());
		
		return "departments";
	}
	
	
	//Add new department - department/add
	@RequestMapping(value = "/add") 
	public String addDepartment(Model model) {
		model.addAttribute("department", new Department());
		
		ArrayList<Employee> managerId = employeeService.findAllEmployees();
		model.addAttribute("employees", managerId);
		
		return "department-form";
	}
	
	
	//Save new department - department/save
	@RequestMapping(value = "/save")
	public String saveDepartment(@ModelAttribute("department") @Valid Department department, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			
			return "department-form";
		}
		
		deptService.saveDepartment(department);
		
		return "forward:/department/all";
	}
	
	
	
	//Edit department - department/editDeparment/id
		@RequestMapping("/editDepartment/{id}")
		public String editDepartment(@PathVariable("id") Integer id, Model model) {
			
			Department d = deptService.findById(id);
			model.addAttribute("department", d);
			
			ArrayList<Employee> managerId = employeeService.findAllEmployees();
			model.addAttribute("employees", managerId);
			
			return "department-edit";
		}
		
		
		//Return successful edit - department/editDepartmentSuccess/id
		@PostMapping("/editDepartmentSuccess/{id}")
		public String editedDepartment(@PathVariable("id") Integer id, @ModelAttribute("d") Department department, Model model) {
			
			deptService.editDepartment(department);
		
			return "forward:/department/all";
		}


		
		//Delete existing department - department/delete/id
		@RequestMapping(value = "/delete/{id}")
		public String deleteDepartment(@PathVariable("id") Integer id) {
			
			deptService.deleteDepartment(deptService.findById(id));
			
			return "forward:/department/all";
		}

}
