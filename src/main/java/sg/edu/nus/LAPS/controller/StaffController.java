package sg.edu.nus.LAPS.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import sg.edu.nus.LAPS.model.ApprovalStatus;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.services.EmployeeService;
import sg.edu.nus.LAPS.services.LeaveApplicationService;
import sg.edu.nus.LAPS.services.LeaveTypeService;
import sg.edu.nus.LAPS.services.PDFGenerateService;

@Controller
@RequestMapping("/employee")
public class StaffController {
	
    @Autowired
    LeaveApplicationService leaveApplicationService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    LeaveTypeService leaveTypeService;
	@Autowired
	PDFGenerateService pdfGenerateService;
	
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
    public String saveLeave(@ModelAttribute("newLeave") LeaveApplication LA,@ModelAttribute("employee") Employee employee){
    	
    	LA.setEmployee(employee);
        LA.setApprovalStatus(ApprovalStatus.APPLIED);

        // Integer id = employee.getEmployeeId();
        leaveApplicationService.saveLeaveApplication(LA);
        return "home";
    }
    
    @RequestMapping("/manageLeave/{id}")
    public String manageLeave(@PathVariable("id") Integer id, Model model){
        List<LeaveApplication> list = new ArrayList<LeaveApplication>();
        list.addAll(leaveApplicationService.findAllLeaves(id));
        model.addAttribute("leaveList", list);
        model.addAttribute("employee", employeeService.findEmployeeById(id));
        
        return "leaveManager";
    }
    
    @RequestMapping(value = "/editLeave/{id}", method = RequestMethod.GET)
	public String editLeave(@PathVariable("id") Integer id, Model model, @ModelAttribute Employee employee) {
    	LeaveApplication leaveAppToChange = leaveApplicationService.findSingleLeaveById(id);
    	//model.addAttribute("leave", leaveAppToChange);
    	model.addAttribute("newLeave", leaveAppToChange);
		
		return "leaveForm-edit";
	}
    
    @RequestMapping(value = "/updateLeave/{id}")
	public String updateLeave(@PathVariable("id") Integer id, Model model, @ModelAttribute @Valid LeaveApplication LA, BindingResult bdgresult) {
    	if(bdgresult.hasErrors())
        {
            return "leaveForm-edit";
        }
    	
    	LeaveApplication leaveAppToChange = leaveApplicationService.findSingleLeaveById(id);
    	
    	// update object attributes
    	leaveAppToChange.setFromDate(LA.getFromDate());
    	leaveAppToChange.setToDate(LA.getToDate());
    	leaveAppToChange.setRemarks(LA.getRemarks());
    	leaveAppToChange.setCoveringEmp(LA.getCoveringEmp());
    	leaveAppToChange.setLeaveType(LA.getLeaveType());
    	leaveAppToChange.setApprovalStatus(ApprovalStatus.UPDATED);
    	// save changes
    	leaveApplicationService.saveLeaveApplication(leaveAppToChange);
    	
    	model.addAttribute("newLeave", leaveAppToChange);
		
		return "home";
	}
    
    @RequestMapping("/deleteLeave/{id}")
	public String deleteLeave(@PathVariable("id") Integer id, Model model) {
		leaveApplicationService.deleteLeave(id);
		return "home";
	}
    
    @RequestMapping("/cancelLeave/{id}")
	public String cancelLeave(@PathVariable("id") Integer id, Model model, @ModelAttribute LeaveApplication LA) {
    	
    	LeaveApplication leaveAppToChange = leaveApplicationService.findSingleLeaveById(id);
    	leaveAppToChange.setApprovalStatus(ApprovalStatus.CANCELLED);
    	leaveApplicationService.saveLeaveApplication(leaveAppToChange);
		return "home";
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

	@GetMapping("/download/{id}")
	public void downloadList(HttpServletResponse response,@ModelAttribute("id") Integer eid) throws DocumentException, IOException{
		List<LeaveApplication> list = leaveApplicationService.findAllLeaves(eid);
		response.setContentType("test/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
		pdfGenerateService.export(response,(ArrayList<LeaveApplication>) list);
	}
}
