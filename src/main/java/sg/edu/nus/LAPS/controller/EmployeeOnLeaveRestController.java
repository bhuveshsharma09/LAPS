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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/all")
public class EmployeeOnLeaveRestController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    LeaveApplicationService leaveApplicationService;

    @RequestMapping(value = "/onLeave")
    public List<Map> onLeave(Model model)
    {
        List<LeaveApplication> leaveApplicationList = leaveApplicationService.findAllById(0);
        List<LeaveApplication> approvedLeaves = new ArrayList<LeaveApplication>();
        for (LeaveApplication l:leaveApplicationList
        ) {
            if (l.getApprovalStatus()==ApprovalStatus.APPROVED)
            {
                approvedLeaves.add(l);
            }


        }
        List<Map> leaveElements = new ArrayList<>();


        for (LeaveApplication approvedLeave:approvedLeaves
             ) {
            Map<String, String> temp = new HashMap<>();
            temp.put("Leave Id",String.valueOf(approvedLeave.getLeaveId()));
            temp.put("From Date",String.valueOf(approvedLeave.getFromDate()));
            temp.put("To Date",String.valueOf(approvedLeave.getToDate()));
            temp.put("Employee Name",String.valueOf(approvedLeave.getEmployee().getName()));
            temp.put("Contact Details",String.valueOf(approvedLeave.getContactDetails()));
            temp.put("Covering Employee",String.valueOf(approvedLeave.getCoveringEmp()));
            temp.put("Approval Status",String.valueOf(approvedLeave.getApprovalStatus()));

            leaveElements.add(temp);


        }

        return leaveElements;
    }


}
