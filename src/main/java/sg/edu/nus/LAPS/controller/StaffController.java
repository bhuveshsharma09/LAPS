package sg.edu.nus.LAPS.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.lowagie.text.DocumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.LAPS.model.ApprovalStatus;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.services.EmployeeService;
import sg.edu.nus.LAPS.services.LeaveApplicationService;
import sg.edu.nus.LAPS.services.LeaveTypeService;
import sg.edu.nus.LAPS.services.PDFGenerateService;

@Controller
@RequestMapping(value = "/employee")

public class StaffController {
	
    @Autowired
    LeaveApplicationService leaveApplicationService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    LeaveTypeService leaveTypeService;
	@Autowired
	PDFGenerateService pdfGenerateService;
	@Autowired
	EmailController emailController;

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
        List<Object> leaveType = leaveTypeService.findAllLeaveType();
        model.addAttribute("employee", employeeService.findEmployeeById(id));
        model.addAttribute("newLeave", leaveApplication);
        model.addAttribute("leaveTypeValue", leaveType);
        // System.out.println(leaveType);
        return "leaveForm";
    }
    
    @RequestMapping(value="/saveLeave",method = RequestMethod.POST)
    public String saveLeave(@ModelAttribute("newLeave") @Valid LeaveApplication LA,
							BindingResult bdResult, @ModelAttribute("employee") @Valid Employee employee,
							Model model) throws MessagingException, IOException {
		List<Object> leaveType = leaveTypeService.findAllLeaveType();
		Date fromDate = LA.getFromDate();
		Date toDate = LA.getToDate();
    	if(bdResult.hasErrors()){
			model.addAttribute("leaveTypeValue", leaveType);
			model.addAttribute("wrongDate");
			return "leaveForm";
			//return "forward:/employee/addLeave/"+employee.getEmployeeId();
		}
		if(leaveApplicationService.comapreTwoDates(fromDate, toDate) == false){
			model.addAttribute("leaveTypeValue", leaveType);
			model.addAttribute("wrongDate", "Date is wrong");
			return "leaveForm";
			//return "forward:/employee/addLeave/"+employee.getEmployeeId();
		}
    	LA.setEmployee(employee);
        LA.setApprovalStatus(ApprovalStatus.APPLIED);

        leaveApplicationService.saveLeaveApplication(LA);
		List<LeaveApplication> last = leaveApplicationService.findAllLeaveApplicationSorted();
		System.out.println(last.get(0).getLeaveId());
		// emailController.sendTheEmail(2, last.get(0).getLeaveId(), ApprovalStatus.APPLIED);
        return "forward:/employee/manageLeave/"+employee.getEmployeeId();
    }
    
    @RequestMapping("/manageLeave/{id}")
    public String manageLeave(@PathVariable("id") Integer id, Model model){
        List<LeaveApplication> list = new ArrayList<LeaveApplication>();
        list.addAll(leaveApplicationService.findAllLeaves(id));
        
        model.addAttribute("leaveList", list);
        model.addAttribute("employee", employeeService.findEmployeeById(id));
        
        return "leaveManager";
    }
    
    @RequestMapping("/viewLeaveDetails/{id}")
	public String viewLeaveDetails(@PathVariable("id") Integer id, Model model) {
    	
    	LeaveApplication selectedLeave = leaveApplicationService.findSingleLeaveById(id);
    	Employee selectedEmp = selectedLeave.getEmployee();
    	List<Object> leaveType = leaveTypeService.findAllLeaveType();

    	model.addAttribute("newLeave", selectedLeave);
    	model.addAttribute("employee", selectedEmp);
    	model.addAttribute("leaveTypeValue", leaveType);
    	
		return "leaveDetails";
	}
    
    @RequestMapping(value = "/editLeave/{id}")
	public String editLeave(@PathVariable("id") Integer id, Model model) {
    	
    	LeaveApplication leaveAppToChange = leaveApplicationService.findSingleLeaveById(id);
    	List<Object> leaveType = leaveTypeService.findAllLeaveType();
    	
    	model.addAttribute("newLeave", leaveAppToChange);
    	model.addAttribute("leaveTypeValue", leaveType);
    	model.addAttribute("employee", leaveAppToChange.getEmployee());
    	
		return "leaveForm-edit";
	}
    
    @RequestMapping(value = "/updateLeave/{id}", method = RequestMethod.POST)
	public String updateLeave(@PathVariable("id") Integer id, Model model, @ModelAttribute("newLeave") @Valid LeaveApplication LA, BindingResult bdgresult,
			@ModelAttribute("employee") Employee employee) throws MessagingException, IOException {
    	
    	List<Object> leaveType = leaveTypeService.findAllLeaveType();
		Date fromDate = LA.getFromDate();
		Date toDate = LA.getToDate();
		
		if(leaveApplicationService.comapreTwoDates(fromDate, toDate) == false){
			model.addAttribute("wrongDate", "Date is wrong");
			model.addAttribute("leaveTypeValue", leaveType);
			return "leaveForm-edit";
		}
    	if(bdgresult.hasErrors()){
			model.addAttribute("leaveTypeValue", leaveType);
			model.addAttribute("wrongDate");
			return "leaveForm-edit";
		}
    	
    	// find the leave to change
    	LeaveApplication leaveAppToChange = leaveApplicationService.findSingleLeaveById(id);
    	
    	// update object attributes
    	leaveAppToChange.setFromDate(LA.getFromDate());
    	leaveAppToChange.setToDate(LA.getToDate());
    	leaveAppToChange.setRemarks(LA.getRemarks());
    	leaveAppToChange.setContactDetails(LA.getContactDetails());
    	leaveAppToChange.setCoveringEmp(LA.getCoveringEmp());
    	leaveAppToChange.setLeaveType(LA.getLeaveType());
    	leaveAppToChange.setApprovalStatus(ApprovalStatus.UPDATED);
    	
    	// save changes
    	leaveApplicationService.saveLeaveApplication(leaveAppToChange);
    	
    	// send email to manager
		List<LeaveApplication> last = leaveApplicationService.findAllLeaveApplicationSorted();
		System.out.println(last.get(0).getLeaveId());
		//emailController.sendTheEmail(2, last.get(0).getLeaveId(), ApprovalStatus.UPDATED);
    	
    	model.addAttribute("newLeave", leaveAppToChange);
		
		return "redirect:/employee/manageLeave/" + leaveAppToChange.getEmployee().getEmployeeId();
	}
    	
    @RequestMapping("/deleteLeave/{id}")
	public String deleteLeave(@PathVariable("id") Integer id, Model model, @ModelAttribute LeaveApplication LA) {
		//leaveApplicationService.deleteLeave(id);
		
		LeaveApplication leaveAppToChange = leaveApplicationService.findSingleLeaveById(id);
    	leaveAppToChange.setApprovalStatus(ApprovalStatus.DELETED);
    	leaveApplicationService.saveLeaveApplication(leaveAppToChange);
    	
    	return "redirect:/employee/manageLeave/" + leaveAppToChange.getEmployee().getEmployeeId();
	}
    
    @RequestMapping("/cancelLeave/{id}")
	public String cancelLeave(@PathVariable("id") Integer id, Model model) {
    	
    	LeaveApplication leaveAppToChange = leaveApplicationService.findSingleLeaveById(id);
    	leaveAppToChange.setApprovalStatus(ApprovalStatus.CANCELLED);
    	leaveApplicationService.saveLeaveApplication(leaveAppToChange);
    	
    	// return subtracted leave days to leave count
    	employeeService.updateLeaveCount(leaveAppToChange.getEmployee(), leaveAppToChange);
    	
		return "redirect:/employee/manageLeave/" + leaveAppToChange.getEmployee().getEmployeeId();
	}


    @RequestMapping(value = "/history")
	public String LeaveHistory(HttpSession session, Model model, @RequestParam(value="pageNumber", required=false, defaultValue="1") int pageNumber,
			@RequestParam(value="size", required=false, defaultValue="5") int size) {
		SessionController usession = (SessionController) session.getAttribute("userSession");
		
		if (usession.getUserCredentials() != null) {	
				model.addAttribute("leaveHistory", leaveApplicationService.findAllLeaves(usession.getEmployee().getEmployeeId(), Calendar.getInstance().get(Calendar.YEAR), pageNumber, size));
				model.addAttribute("approvedLeaveHistory", leaveApplicationService.findApprovedLeaves(usession.getEmployee().getEmployeeId(), Calendar.getInstance().get(Calendar.YEAR), pageNumber, size));
				model.addAttribute("rejectedLeaveHistory", leaveApplicationService.findRejectedLeaves(usession.getEmployee().getEmployeeId(), Calendar.getInstance().get(Calendar.YEAR), pageNumber, size));
				model.addAttribute("appliedLeaveHistory", leaveApplicationService.findAppliedLeaves(usession.getEmployee().getEmployeeId(), Calendar.getInstance().get(Calendar.YEAR), pageNumber, size));
				model.addAttribute("updatedLeaveHistory", leaveApplicationService.findUpdatedLeaves(usession.getEmployee().getEmployeeId(), Calendar.getInstance().get(Calendar.YEAR), pageNumber, size));
				model.addAttribute("cancelledLeaveHistory", leaveApplicationService.findCancelledLeaves(usession.getEmployee().getEmployeeId(), Calendar.getInstance().get(Calendar.YEAR), pageNumber, size));
			return "leavehistory";
		}
		return "login";
    }

	@GetMapping("/download/{id}")
	public void downloadList(HttpServletResponse response,@ModelAttribute("id") Integer eid) throws DocumentException, IOException{
		List<LeaveApplication> list = leaveApplicationService.findAllLeaves(eid);
		response.setContentType("test/pdf");
		

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + employeeService.findEmployeeById(eid) + ".pdf";
        response.setHeader(headerKey, headerValue);
		pdfGenerateService.export(response,(ArrayList<LeaveApplication>) list);
	}
}
