package sg.edu.nus.LAPS.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.LAPS.model.ApprovalStatus;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.repo.LeaveApplicationRepository;
import sg.edu.nus.LAPS.services.LeaveApplicationService;

@Controller
public class AdminController {
    @Autowired
    LeaveApplicationService leaveApplicationService;

    @RequestMapping(value = "/onLeave")
    public String onLeave(Model model)
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

        //System.out.println(leaveApplicationList.getRemarks());
        model.addAttribute("leavelist",approvedLeaves);
        return "on-leave";
    }

}
