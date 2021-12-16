package sg.edu.nus.LAPS.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

///import Exceptions.EmployeeNotFound;
//import Validators.EmployeeValidator;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.services.EmployeeService;
import sg.edu.nus.LAPS.services.EmployeeServiceImpl;
import sg.edu.nus.LAPS.services.EmployeeService;



@Controller
@RequestMapping("/employee") //URL
//@SessionAttributes(value = {"session"}, types = {UserSession.class}) //Session
public class AdminEmployeeController {
	
	@Autowired
	EmployeeService empService;

	@Autowired
	public void setEmployeeService(EmployeeServiceImpl empServiceImpl) {
		this.empService = empServiceImpl;
	}
	
	@RequestMapping(value = "/all")
	public String list(Model model) {
		model.addAttribute("employees", empService.findAllEmployees());
		return "employees";
	}
	
	//Add new employee
	@RequestMapping(value = "/add")
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "employee-form";
	}
	@RequestMapping(value = "/save")
	public String saveEmployee(@ModelAttribute("employee") @Valid Employee employee, 
			BindingResult bindingResult,  Model model) {
		if (bindingResult.hasErrors()) {
			return "employee-form";
		}
		empService.saveEmployee(employee);
		return "forward:/employee/all";
	}

	

}