package sg.edu.nus.LAPS.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.LAPS.model.ApprovalStatus;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.repo.LeaveApplicationRepository;
import sg.edu.nus.LAPS.services.EmployeeService;
import sg.edu.nus.LAPS.services.LeaveApplicationService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    LeaveApplicationService leaveApplicationService;
    
	@Autowired
	LeaveApplicationRepository leaveApplicationRepository;

    @RequestMapping("/request/{id}")
    public String getEmployeeLeaves(Model model,@PathVariable Integer id){
        ApprovalStatus applied = ApprovalStatus.APPLIED;
		ApprovalStatus updated = ApprovalStatus.UPDATED;
		List<LeaveApplication> list = leaveApplicationService.findAllLeavesOfEmployeeByManagerIdWithStatus(id, applied);
		list.addAll(leaveApplicationService.findAllLeavesOfEmployeeByManagerIdWithStatus(id, updated));
		
		// sort by employeeId
		Collections.sort(list, new Comparator<LeaveApplication>() {
			@Override
			public int compare(LeaveApplication l1, LeaveApplication l2) {
				return l1.getEmployee().getEmployeeId().compareTo(l2.getEmployee().getEmployeeId());
			}
		});
		
        model.addAttribute("appliedStatusList", list);
        
		
        return "approval-list";
    }
    
	@RequestMapping("/leavelist/{id}")
	public String getAllLeaves(@PathVariable("id") Integer id, Model model) {
		List<LeaveApplication> pendingLeaveList = leaveApplicationRepository.findLeavesByEmployee_employeeIdAndApprovalStatus(id, ApprovalStatus.APPLIED);
		pendingLeaveList.addAll(leaveApplicationRepository.findLeavesByEmployee_employeeIdAndApprovalStatus(id, ApprovalStatus.UPDATED));
		model.addAttribute("pendingLeaveList", pendingLeaveList);
		Date current = new Date();
		List<LeaveApplication> upcomingLeaveList = leaveApplicationRepository.findUpcomingLeaves(id, ApprovalStatus.APPROVED, current);
		model.addAttribute("upcomingLeaveList", upcomingLeaveList);
		List<LeaveApplication> historicalLeaveList = leaveApplicationService.findAllLeaves(id);
		historicalLeaveList.removeAll(pendingLeaveList);
		historicalLeaveList.removeAll(upcomingLeaveList);
		model.addAttribute("historicalLeaveList", historicalLeaveList);
		return "manager-leavelist";
	}
	
	@RequestMapping("/viewLeaveDetails/{id}")
	public String viewLeaveDetails(@PathVariable("id") Integer id, Model model) {
    	
    	LeaveApplication selectedLeave = leaveApplicationService.findSingleLeaveById(id);
    	Employee selectedEmp = selectedLeave.getEmployee();
    	
    	model.addAttribute("selectedLeave", selectedLeave);
    	model.addAttribute("employee", selectedEmp);
    	
		return "leaveDetailsForApproval";
	}
	
	@RequestMapping(value = "/approveLeave/{id}", method = RequestMethod.POST)
	public String approveLeave(@PathVariable("id") Integer id, @RequestParam("approve_reject") String approvalResult, @RequestParam("manager-remarks") String managerComment, Model model) {
    	
    	LeaveApplication leaveAppToApprove = leaveApplicationService.findSingleLeaveById(id);
    	
    	if (approvalResult.equalsIgnoreCase("Approve")) {
    		leaveAppToApprove.setApprovalStatus(ApprovalStatus.APPROVED);
    	}
    	else if (approvalResult.equalsIgnoreCase("Reject")) {
    		leaveAppToApprove.setManagerComment(managerComment);
    		leaveAppToApprove.setApprovalStatus(ApprovalStatus.REJECTED);
    	}
    	
    	leaveApplicationService.saveLeaveApplication(leaveAppToApprove);
    	
		return "home";
	}
}
