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

import sg.edu.nus.LAPS.model.Role;
import sg.edu.nus.LAPS.services.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	RoleService rService;
	
	@RequestMapping("/all")
	public String findAllRoles(Model model) {
		ArrayList<Role> all = rService.findAllRoles();
		model.addAttribute("roles", all);
		return "role-list";
	}
	
	@RequestMapping("/load")
	public String loadRolesForm(Model model) {
		Role role = new Role();
		model.addAttribute("role", role);
		return "role-form";
	}
	
	@PostMapping("/addRole")
	public String addRole(@ModelAttribute("role") @Valid Role role, BindingResult bingResult) {
		if (bingResult.hasErrors()) {
			return "role-form";
		}
		rService.createRole(role);
		return "forward:/role/all";

	}

	@RequestMapping("/edit/{id}")
	public String editRole(@PathVariable("id") Integer id, Model model) {
		Role role = rService.findRoleById(id);
		model.addAttribute("role", role);
		return "role-form";
	}

	@RequestMapping("/delete/{id}")
	public String deleteRole(@PathVariable("id") Integer id, Model model) {
		Role role = rService.findRoleById(id);
		rService.deleteRole(role);
		return "forward:/role/all";
	}

}
