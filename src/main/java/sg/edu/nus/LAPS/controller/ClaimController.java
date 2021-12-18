package sg.edu.nus.LAPS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.edu.nus.LAPS.model.Claim;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.services.ClaimService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/Claim")
public class ClaimController {

    @Autowired
    ClaimService claimService;

    @RequestMapping(value = "/all")
    public String list(Model model) {
        model.addAttribute("claims", claimService.findAllClaims());
        return "claims";
    }

    @RequestMapping(value = "/save")
    public String saveClaim(@ModelAttribute("claim") @Valid Claim claim,
                               BindingResult bindingResult, Model model) {

        System.out.println("here");
       /* if (bindingResult.hasErrors()) {
            return "claim-form";
        }*/
        claimService.saveClaimRequest(claim);
        return "forward:/Claim/all";
    }


}
