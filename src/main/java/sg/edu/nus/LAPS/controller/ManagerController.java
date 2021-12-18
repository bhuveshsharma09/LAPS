package sg.edu.nus.LAPS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.LAPS.services.LeaveApplicationService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    LeaveApplicationService leaveApplicationService;

    @RequestMapping("/request/{id}")
    public String getEmployeeLeaves(Model model,@PathVariable Integer id){
        model.addAttribute("leaveRequestList", leaveApplicationService.findAllLeavesOfEmployeeByManagerId(id));
        
        return "approval-form";
    }
}
