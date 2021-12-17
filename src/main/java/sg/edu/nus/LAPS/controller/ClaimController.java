/*
package sg.edu.nus.LAPS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.edu.nus.LAPS.model.Employee;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/Claim")
public class ClaimController {




    @RequestMapping(value = "/save")
    public String saveEmployee(@ModelAttribute("employee") @Valid Employee employee,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "employee-form";
        }
        employeeService.saveEmployee(employee);
        return "forward:/employee/all";
    }


}
*/
