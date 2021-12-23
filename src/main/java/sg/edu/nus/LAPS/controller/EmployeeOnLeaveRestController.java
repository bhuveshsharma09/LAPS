package sg.edu.nus.LAPS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.edu.nus.LAPS.model.ApprovalStatus;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.repo.ClaimRepository;
import sg.edu.nus.LAPS.services.ClaimService;
import sg.edu.nus.LAPS.services.EmployeeService;
import sg.edu.nus.LAPS.services.LeaveApplicationService;
import sg.edu.nus.LAPS.validators.ClaimValidator;

import java.util.List;

@RestController
@RequestMapping(value = "/all")
public class EmployeeOnLeaveRestController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    LeaveApplicationService leaveApplicationService;

    @RequestMapping(value = "/onLeave")
    public List<LeaveApplication> onLeave(Model model)
    {
       // List<LeaveApplication> leaveApplicationList = leaveApplicationService.findAllById(0, ApprovalStatus.APPROVED);

        //System.out.println(leaveApplicationList.getRemarks());
       // model.addAttribute("leavelist",leaveApplicationList);
         return null;
    }


}
