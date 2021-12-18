package sg.edu.nus.LAPS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.SessionAttributes;
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
        claimService.saveClaimRequest(claim);
        return "forward:/Claim/all";
    }

    @RequestMapping(value = "/all")
    public String list(Model model, HttpSession httpSession) {
        SessionController sessionController = (SessionController) httpSession.getAttribute("userSession");
        System.out.println(sessionController.getEmployee().getEmployeeId());
        model.addAttribute("claimList", claimService.findClaimByEmployeeId(sessionController.getEmployee().getEmployeeId()));
        return "claims";
    }




}
