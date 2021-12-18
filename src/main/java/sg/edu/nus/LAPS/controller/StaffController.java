package sg.edu.nus.LAPS.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.LAPS.model.Claim;
import sg.edu.nus.LAPS.services.LeaveApplicationService;

@Controller
@RequestMapping("/employee")
public class StaffController {
	
    @Autowired
    LeaveApplicationService leaveApplicationService;

    @RequestMapping("/leaveList/{id}")
    public String getAllLeaves(@PathVariable("id") Integer id, Model model){
        model.addAttribute("leaveList", leaveApplicationService.findAllLeaves(id));
        return "leaveList";
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
