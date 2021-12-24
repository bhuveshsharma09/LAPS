package sg.edu.nus.LAPS.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.model.Role;
import sg.edu.nus.LAPS.model.UserCredentials;
import sg.edu.nus.LAPS.services.EmployeeService;
import sg.edu.nus.LAPS.services.RoleService;
import sg.edu.nus.LAPS.services.UserCredentialsService;


@Controller
@RequestMapping("/adminuser")
public class AdminUserController {
	
	@Autowired
	UserCredentialsService ucService;
	
	@Autowired
	RoleService rService;
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping("/all")
	public String findAllUsers(Model model) {
		List<UserCredentials> all = ucService.findAllUserCredentials();
		model.addAttribute("ulist", all);
		
		ArrayList<Role> roles = rService.findAllRoles();
		model.addAttribute("rolesall", roles);
		
		return "userList";
	}
	
	@RequestMapping("/add")
	public String newUserPage(Model model) {
		UserCredentials uc = new UserCredentials();
		model.addAttribute("user", uc);
		ArrayList<Role> roles = rService.findAllRoles();
		model.addAttribute("rolesall", roles);
		ArrayList<Employee> allEmployees1 = employeeService.findAllEmployees();
		ArrayList<Employee> allEmployees = new ArrayList<>();
		
		for(Employee emp : allEmployees1)
		{
			if(emp.getUserCredentials() == null)
			{
				allEmployees.add(emp);
			}
		}
		model.addAttribute("allEmployees", allEmployees);
		
		return "userForm";
	}
	
	@PostMapping("/adduser")
	public String addUser(@ModelAttribute("user") @Valid UserCredentials user,BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "userForm";
		} 
		ArrayList<Role> newRoles = new ArrayList<Role>();
		 for (Iterator<Role> iterator = user.getRoles().iterator(); iterator.hasNext();) {
				Role role = (Role) iterator.next();
				Role newRole = rService.findRoleByName(role.getRoleDesc());
				newRoles.add(newRole);				
		 }
		user.setRoles(newRoles);
		
		Employee employee = employeeService.findEmployeeByName1(user.getEmployee().getName());
		employeeService.saveEmployee(employee);
		user.setEmployee(employee);
		
		ucService.createUser(user);
		return "forward:/adminuser/all";
				 
	}
	
	@RequestMapping("/edit/{id}")
	public String editUser(@PathVariable("id") Integer id, Model model) {
		UserCredentials uc = ucService.findByUserId(id);
		model.addAttribute("user", uc);
		ArrayList<Role> roles = rService.findAllRoles();
		model.addAttribute("rolesall", roles);
		
		ArrayList<Employee> allEmployees = employeeService.findAllEmployees();
		model.addAttribute("allEmployees", allEmployees);
		
//		return "userForm";
		
		return "userEdit";
	}
	
	@PostMapping("/edit/{id}")
	public String finishEditUser(@PathVariable("id") Integer id, @ModelAttribute("user") @Valid UserCredentials user,BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "userEdit";
		} 
		ucService.eidtUser(user);
		return "forward:/adminuser/all";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		UserCredentials user = ucService.findByUserId(id);
		
		ucService.deleteUser(user);
		return "forward:/adminuser/all";
		
	}

}
