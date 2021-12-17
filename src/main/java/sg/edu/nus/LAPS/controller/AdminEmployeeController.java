package sg.edu.nus.LAPS.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

///import Exceptions.EmployeeNotFound;
//import Validators.EmployeeValidator;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.services.EmployeeService;
import sg.edu.nus.LAPS.services.EmployeeServiceImpl;



@Controller
@RequestMapping("/employee") //URL
//@SessionAttributes(value = {"session"}, types = {UserSession.class}) //Session
public class AdminEmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@Autowired
	public void setEmployeeService(EmployeeServiceImpl empServiceImpl) {
		this.employeeService = empServiceImpl;
	}
	
	//View a list of all employees
	@RequestMapping(value = "/all")
	public String list(Model model) {
		model.addAttribute("employees", employeeService.findAllEmployees());
		return "employees";
	}
	
	//Add new employee
	@RequestMapping(value = "/add")
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "employee-form";
	}
	
	//Edit existing employee
	@RequestMapping(value = "/edit/{id}")
	public String editEmployee(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("employee", employeeService.findEmployeeById(id));
		return "employee-form";
	}
	
	//Save new and edited employee
	@RequestMapping(value = "/save")
	public String saveEmployee(@ModelAttribute("employee") @Valid Employee employee, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			return "employee-form";
		}
		employeeService.saveEmployee(employee);
		return "forward:/employee/all";
	}

	//Delete existing employee
	@RequestMapping(value = "/delete/{id}")
	public String deleteEmployee(@PathVariable("id") Integer id) {
		employeeService.deleteEmployee(employeeService.findEmployeeById(id));
		return "forward:/employee/all";
	}


}