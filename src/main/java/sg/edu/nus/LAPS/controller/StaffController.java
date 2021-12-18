package sg.edu.nus.LAPS.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.nus.LAPS.model.ApprovalStatus;
import sg.edu.nus.LAPS.model.Claim;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.services.EmployeeService;
import sg.edu.nus.LAPS.services.LeaveApplicationService;
import sg.edu.nus.LAPS.services.LeaveTypeService;

@Controller
@RequestMapping("/employee")
public class StaffController {
	
    @Autowired
    LeaveApplicationService leaveApplicationService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    LeaveTypeService leaveTypeService;
    @RequestMapping("/leaveList/{id}")
    public String getAllLeaves(@PathVariable("id") Integer id, Model model){
        List<LeaveApplication> list = new ArrayList<LeaveApplication>();
        list.addAll(leaveApplicationService.findAllLeaves(id));
        model.addAttribute("leaveList", list);
        return "leaveList";
    }
    @RequestMapping("/addLeave/{id}")
    public String addLeave(Model model,@PathVariable("id") Integer id){
        LeaveApplication leaveApplication = new LeaveApplication();
        model.addAttribute("employee", employeeService.findEmployeeById(id));
        model.addAttribute("newLeave", leaveApplication);
        return "leaveForm";
    }
    @RequestMapping(value="/saveLeave",method = RequestMethod.POST)
    public String saveLeave(@ModelAttribute("newLeave") LeaveApplication LA,@ModelAttribute("employee") Employee employee){
        LA.setEmployee(employee);
        LA.setApprovalStatus(ApprovalStatus.APPLIED);
        // Integer id = employee.getEmployeeId();
        leaveApplicationService.saveLeaveApplication(LA);
        return "home";
    }

	@RequestMapping(value = "/claim")
	    public String applyClaim(Model model)
	    {
	        model.addAttribute("claim",new Claim());
	        return "claim-form";
	    }

	@RequestMapping(value = "/history")
	public String LeaveHistory(HttpSession session, Model model) {
		SessionController usession = (SessionController) session.getAttribute("userSession");
		//ModelAndView mav = new ModelAndView("login");
		if (usession.getUserCredentials() != null) {
			//mav = new ModelAndView("staff-course-history");
			//System.out.println(usession.getEmployee());
			if (leaveApplicationService.findPastLeavesByEmployeeId(usession.getEmployee().getEmployeeId()).size() > 0) {
				model.addAttribute("leaveHistory", leaveApplicationService.findPastLeavesByEmployeeId(usession.getEmployee().getEmployeeId()));
			}
			return "leavehistory";
		}
		return "login";
	}



}
