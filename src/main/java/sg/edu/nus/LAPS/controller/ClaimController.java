package sg.edu.nus.LAPS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import sg.edu.nus.LAPS.model.ApprovalStatus;
import sg.edu.nus.LAPS.model.Claim;
import sg.edu.nus.LAPS.services.ClaimService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/Claim")
@SessionAttributes(value = {"userSession"}, types = {SessionController.class}) //Session
public class ClaimController {

    @Autowired
    ClaimService claimService;

    @RequestMapping(value = "/new")
    public String getClaimForm(Model model)
    {
        model.addAttribute("claim", new Claim());
        return "claim-form";
    }

    @RequestMapping(value = "/save")
    public String saveClaim(@ModelAttribute("claim") @Valid Claim claim
            , BindingResult bindingResult
            , Model model
            , HttpSession httpSession) {

        System.out.println("inside the /Claim/save function");
       /* if (bindingResult.hasErrors()) {
            return "claim-form";
        }*/
        SessionController sessionController = (SessionController) httpSession.getAttribute("userSession");
        claim.setEmployee(sessionController.getEmployee());
        claim.setApprovalStatus(ApprovalStatus.APPLIED);
        claimService.saveClaimRequest(claim);
        return "forward:/Claim/all";
    }

    @RequestMapping(value = "/all")
    public String getAllClaimsByEmployeeId(Model model, HttpSession httpSession) {
        SessionController sessionController = (SessionController) httpSession.getAttribute("userSession");
        System.out.println(sessionController.getEmployee().getEmployeeId());
        model.addAttribute("claimList", claimService.findClaimByEmployeeId(sessionController.getEmployee().getEmployeeId()));
        return "all-claims";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editClaim(@PathVariable("id") Integer id
            , Model model
            , HttpSession httpSession)
    {
        //SessionController sessionController = (SessionController) httpSession.getAttribute("userSession");
        model.addAttribute("claim",claimService.findClaimById(id));
        return "claim-form-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editClaim(@PathVariable("id") Integer id
            , @ModelAttribute @Valid Claim claim
            , BindingResult bindingResult
            , Model model
            , HttpSession httpSession)
    {
        if(bindingResult.hasErrors())
        {
            return "claim-form-edit";
        }
        Claim originalClaimObj = claimService.findClaimById(id);

        // pack the object
        originalClaimObj.setEligibleClaim(claim.getEligibleClaim());
        originalClaimObj.setOvertimeDate(claim.getOvertimeDate());
        originalClaimObj.setHoursWorked(claim.getHoursWorked());
        originalClaimObj.setRemarks(claim.getRemarks());
        originalClaimObj.setApprovalStatus(ApprovalStatus.UPDATED);

        claimService.changeClaim(originalClaimObj);
        SessionController sessionController = (SessionController)  httpSession.getAttribute("userSession");
        model.addAttribute("claimList", claimService.findClaimByEmployeeId(sessionController.getEmployee().getEmployeeId()));
        return "all-claims";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteClaim(@PathVariable("id") Integer id
            , Model model
            , HttpSession httpSession)
    {
        Claim claim = claimService.findClaimById(id);
        //claimService.removeClaim(claim);
        claim.setApprovalStatus(ApprovalStatus.DELETED);
        claimService.saveClaimRequest(claim);

        SessionController sessionController = (SessionController)  httpSession.getAttribute("userSession");
        model.addAttribute("claimList", claimService.findClaimByEmployeeId(sessionController.getEmployee().getEmployeeId()));
        return "all-claims";
    }






}
