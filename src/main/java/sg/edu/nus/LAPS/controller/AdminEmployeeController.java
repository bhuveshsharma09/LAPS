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

import sg.edu.nus.LAPS.services.DepartmentService;
import sg.edu.nus.LAPS.services.DepartmentServiceImpl;
import sg.edu.nus.LAPS.model.Department;
///import Exceptions.EmployeeNotFound;
//import Validators.EmployeeValidator;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.services.EmployeeService;
import sg.edu.nus.LAPS.services.EmployeeServiceImpl;


@Controller
@RequestMapping("/employee") //URL
public class AdminEmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@Autowired
	public void setEmployeeService(EmployeeServiceImpl empServiceImpl) {
		this.employeeService = empServiceImpl;
	}

	
	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	public void setDepartmentService(DepartmentServiceImpl deptServiceImpl) {
		this.deptService = deptServiceImpl;
	}
	
	//View a list of all employees - employee/all
	@RequestMapping(value = "/all")
	public String list(Model model) {
		model.addAttribute("employees", employeeService.findAllEmployees());
		
		return "employees";
	}
	
	
	//Add new employee - employee/add
	@RequestMapping(value = "/add")
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		
		ArrayList<Employee> emps = employeeService.findAllEmployees();
		model.addAttribute("employees", emps);
		
		ArrayList<Department> depts = deptService.findAllDepartments();
		model.addAttribute("departments", depts);
		
		return "employee-form";
	}
	
	
	//Save new employee - employee/save
	@RequestMapping(value = "/save")
	public String saveEmployee(@ModelAttribute("employee") @Valid Employee employee, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			
			return "employee-form";
		}
		employeeService.saveEmployee(employee);
		
		return "forward:/employee/all";
	}

	
	//Edit Employee - employee/edit/id
	@RequestMapping("/edit/{id}")
	public String editEmployee(@PathVariable("id") Integer id, Model model) {
		Employee e = employeeService.findEmployeeById(id);
		model.addAttribute("employee", e);
		
		ArrayList<Employee> managerId = employeeService.findAllEmployees();
		model.addAttribute("employees", managerId);
		
		ArrayList<Department> depts = deptService.findAllDepartments();
		model.addAttribute("departments", depts);
		
		return "employee-edit";
	}
	
	
	//Return successful edit - employee/editsuccess/id
	@PostMapping("/editsuccess/{id}")
	public String editedEmployee(@PathVariable("id") Integer id, @ModelAttribute("e") Employee employee, Model model) {
		employeeService.editEmployee(employee);
		
		return "forward:/employee/all";
	}
	
	
	//Edit employee leave entitlement - employee/editLeave/id
	@RequestMapping("/editLeaveEntitlement/{id}")
	public String editEmployeeLeave(@PathVariable("id") Integer id, Model model) {
		Employee e = employeeService.findEmployeeById(id);
		model.addAttribute("employee", e);
		
		ArrayList<Employee> managerId = employeeService.findAllEmployees();
		model.addAttribute("employees", managerId);
		
		return "employee-edit-leave";
	}
	
	
	//Successfully edited leave entitlement - employee/editLeaveSuccess/id
	@PostMapping("/editLeaveSuccess/{id}")
	public String editedEmployeeLeave(@PathVariable("id") Integer id, @ModelAttribute("e") Employee employee, Model model) {
		employeeService.editEmployee(employee);
		
		return "forward:/employee/all";
	}
	
	
	//Delete existing employee
	@RequestMapping(value = "/delete/{id}")
	public String deleteEmployee(@PathVariable("id") Integer id) {
		employeeService.deleteEmployee(employeeService.findEmployeeById(id));
		
		return "forward:/employee/all";
	}


}