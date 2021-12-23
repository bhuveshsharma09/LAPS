package sg.edu.nus.LAPS.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sg.edu.nus.LAPS.model.ApprovalStatus;
import sg.edu.nus.LAPS.model.Claim;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.repo.ClaimRepository;
import sg.edu.nus.LAPS.repo.LeaveApplicationRepository;
import sg.edu.nus.LAPS.services.ClaimService;
import sg.edu.nus.LAPS.services.EmployeeService;
import sg.edu.nus.LAPS.services.LeaveApplicationService;

@Controller
@SessionAttributes(value = {"userSession"}, types = {SessionController.class}) //Session
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	EmailController emailController;

	@Autowired
	EmployeeService employeeService;

    @Autowired
    LeaveApplicationService leaveApplicationService;

	@Autowired
	ClaimService claimService;
    
	@Autowired
	LeaveApplicationRepository leaveApplicationRepository;

	@Autowired
	ClaimRepository claimRepository;

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

	// adding function to approve or reject the claims made by subordinate
	@RequestMapping("/claimRequest/{id}")
	public String getEmployeeClaims(Model model,@PathVariable Integer id){
		ApprovalStatus applied = ApprovalStatus.APPLIED;
		ApprovalStatus updated = ApprovalStatus.UPDATED;

		List<Claim> claims = claimRepository.findAllClaimsOfEmployeeByManagerIdWithStatus(id, applied);
		claims.addAll(claimRepository.findAllClaimsOfEmployeeByManagerIdWithStatus(id, updated));

		// sort by employeeId
		Collections.sort(claims, new Comparator<Claim>() {
			@Override
			public int compare(Claim c1, Claim c2) {
				return c1.getEmployee().getEmployeeId().compareTo(c2.getEmployee().getEmployeeId());
			}
		});

		model.addAttribute("appliedStatusList", claims);


		return "approval-claim-list";
	}


    
    @RequestMapping("/leavelist/{id}")
	public String getAllLeaves(@PathVariable("id") Integer id, Model model,
			@RequestParam(value="pageNumber", required=false, defaultValue="1") int pageNumber,
			@RequestParam(value="size", required=false, defaultValue="5") int size) {
		model.addAttribute("pendingLeaveList", leaveApplicationService.findLeavesByEmployee_employeeIdAndApprovalStatusIn(id, List.of(ApprovalStatus.APPLIED, ApprovalStatus.UPDATED)));
		Date current = new Date();
		List<LeaveApplication> upcomingLeaveList = leaveApplicationService.findUpcomingLeavesForEmployee(id, current);
		model.addAttribute("upcomingLeaveList", upcomingLeaveList);
		model.addAttribute("historicalLeaveList", leaveApplicationService.findAllLeavesByEmployeeIdWithPage(id, pageNumber, size));
		model.addAttribute("id", id);
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

	// function for manager to view the claim details
	@RequestMapping("/viewClaimDetails/{id}")
	public String viewClaimDetails(@PathVariable("id") Integer id, Model model) {

		Claim selectedClaim = claimService.findClaimById(id);
		Employee selectedEmp = selectedClaim.getEmployee();

		model.addAttribute("selectedClaim", selectedClaim);
		model.addAttribute("employee", selectedEmp);

		return "claimDetailsForApproval";
	}
	
	@RequestMapping(value = "/approveLeave/{id}", method = RequestMethod.POST)
	public String approveLeave(@PathVariable("id") Integer id, @RequestParam("approve_reject") String approvalResult, 
			@RequestParam("manager-remarks") String managerComment, Model model, HttpSession httpSession) throws MessagingException, IOException {
    	
    	LeaveApplication leaveAppToApprove = leaveApplicationService.findSingleLeaveById(id);
    	
    	if (approvalResult.equalsIgnoreCase("Approve")) {
    		leaveAppToApprove.setManagerComment(managerComment);
    		leaveAppToApprove.setApprovalStatus(ApprovalStatus.APPROVED);
    		
    		// subtract leave duration from leave count
    		employeeService.updateLeaveCount(leaveAppToApprove.getEmployee(), leaveAppToApprove);
    		
    	}
    	else if (approvalResult.equalsIgnoreCase("Reject")) {
    		leaveAppToApprove.setManagerComment(managerComment);
    		leaveAppToApprove.setApprovalStatus(ApprovalStatus.REJECTED);
    	
    	}
    	leaveApplicationService.saveLeaveApplication(leaveAppToApprove);
    	
    	// send email to staff
    	//emailController.sendTheEmailToEmp(2, leaveAppToApprove.getLeaveId(), leaveAppToApprove.getApprovalStatus());
    	
    	SessionController sessionController = (SessionController) httpSession.getAttribute("userSession");
    	
    	
    	
		return "redirect:/manager/request/" + sessionController.getEmployee().getEmployeeId();
	}

	// approve claim
	@RequestMapping(value = "/approveClaim/{id}", method = RequestMethod.POST)
	public String approveClaim(@PathVariable("id") Integer id, @RequestParam("approve_reject") String approvalResult, @RequestParam("manager-remarks") String managerComment, Model model) throws MessagingException, IOException {

		ApprovalStatus approvalStatus1 = null;
		Claim claimToApprove = claimService.findClaimById(id);

		if (approvalResult.equalsIgnoreCase("Approve")) {
			claimToApprove.setManagerComment(managerComment);
			claimToApprove.setApprovalStatus(ApprovalStatus.APPROVED);
			approvalStatus1 = ApprovalStatus.APPROVED;

			Employee employee=claimService.findClaimById(id).getEmployee();
			employee.setCompensationLeaveCount(employee.getCompensationLeaveCount()+0.5);
			employeeService.saveEmployee(employee);



		}
		else if (approvalResult.equalsIgnoreCase("Reject")) {
			claimToApprove.setManagerComment(managerComment);
			claimToApprove.setApprovalStatus(ApprovalStatus.REJECTED);
			approvalStatus1 = ApprovalStatus.REJECTED;
		}

		claimService.saveClaimRequest(claimToApprove);
		emailController.sendTheEmailToEmp(1,claimToApprove.getClaimId(),approvalStatus1);
		// add +1 in CL of Employee


		return "home";
	}


	@RequestMapping(value = "/claim/all")
	public String getSubordinateClaimHistory(HttpSession httpSession, Model model)
	{
		SessionController sessionController = (SessionController) httpSession.getAttribute("userSession");

		System.out.println(sessionController.getEmployee().getEmployeeId());

		model.addAttribute("claimList", claimRepository.findAllClaimsOfEmployeeByManagerIdWithStatus(sessionController.getEmployee().getEmployeeId(), ApprovalStatus.APPROVED));
		return "all-claims-for-manager";
	}


}
