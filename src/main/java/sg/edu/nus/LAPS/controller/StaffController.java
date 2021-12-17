package sg.edu.nus.LAPS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.services.EmployeeService;
import sg.edu.nus.LAPS.services.LeaveApplicationService;

@Controller
@RequestMapping("/employee")
public class StaffController {
	
    @Autowired
    LeaveApplicationService leaveApplicationService;
    @Autowired
    EmployeeService employeeService;
    @RequestMapping("/leaveList/{id}")
    public String getAllLeaves(@PathVariable("id") Integer id, Model model){
        model.addAttribute("leaveList", leaveApplicationService.findAllLeaves(id));
        return "leaveList";
    }
    @RequestMapping("/addLeave/{id}")
    public String addLeave(Model model,@PathVariable("id") Integer id){
        LeaveApplication leaveApplication = new LeaveApplication();
        model.addAttribute("employeeId", employeeService.findEmployeeById(id).getEmployeeId());
        model.addAttribute("newLeave", leaveApplication);
        return "leaveForm";
    }
    @RequestMapping(value="/saveLeave",method = RequestMethod.POST)
    public String saveLeave(@ModelAttribute("newLeave") LeaveApplication LA){
        leaveApplicationService.saveLeaveApplication(LA);
        return "forward:/home";
    }
}
