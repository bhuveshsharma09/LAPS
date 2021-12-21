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

import sg.edu.nus.LAPS.model.LeaveType;
import sg.edu.nus.LAPS.services.LeaveTypeService;


@Controller
@RequestMapping("/leavetype")
public class LeaveTypeController {
	
	@Autowired
	LeaveTypeService ltService;
	
	@RequestMapping("/all")
	public String findAllLeaveTypes(Model model) {
		ArrayList<LeaveType> all = ltService.findAllLeaveTypes();
		model.addAttribute("leaveTypes", all);
		return "ltlisting";
	}
	
	@RequestMapping("/load")
	public String loadLeaveTypesForm(Model model) {
		LeaveType lt = new LeaveType();
		model.addAttribute("leaveType", lt);
		return "ltform";
	}
	
	@PostMapping("/addLeaveType")
	public String addLeaveType(@ModelAttribute("leaveType") @Valid LeaveType leaveType, BindingResult bingResult) {
		if (bingResult.hasErrors()) {
			return "ltform";
		}
		ltService.createLeaveType(leaveType);
		return "forward:/leavetype/all";

	}

	@RequestMapping("/edit/{name}")
	public String editLeaveType(@PathVariable("name") String name, Model model) {
		LeaveType lt = ltService.findLeaveTypeByName(name);
		model.addAttribute("leaveType", lt);
		return "ltform";
	}

	@RequestMapping("/delete/{name}")
	public String deleteLeaveType(@PathVariable("name") String name, Model model) {
		LeaveType lt = ltService.findLeaveTypeByName(name);
		ltService.deleteLeaveType(lt);
		return "forward:/leavetype/all";
	}

	
}
