package sg.edu.nus.LAPS.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.LAPS.model.ApprovalStatus;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.repo.LeaveApplicationRepository;
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
	
	@RequestMapping("/approveLeave/{id}")
	public String approveLeave(@PathVariable("id") Integer id, Model model, @ModelAttribute LeaveApplication LA) {
    	
    	LeaveApplication leaveAppToChange = leaveApplicationService.findSingleLeaveById(id);
    	leaveAppToChange.setApprovalStatus(ApprovalStatus.APPROVED);
    	leaveApplicationService.saveLeaveApplication(leaveAppToChange);
		return "home";
	}
	
	@RequestMapping("/rejectLeave/{id}")
	public String rejectLeave(@PathVariable("id") Integer id, Model model, @ModelAttribute LeaveApplication LA) {
    	
    	LeaveApplication leaveAppToChange = leaveApplicationService.findSingleLeaveById(id);
    	leaveAppToChange.setApprovalStatus(ApprovalStatus.REJECTED);
    	leaveApplicationService.saveLeaveApplication(leaveAppToChange);
		return "home";
	}
}
