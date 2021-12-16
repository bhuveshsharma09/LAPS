package sg.edu.nus.LAPS.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.model.UserCredentials;
import sg.edu.nus.LAPS.repo.EmployeeRepository;
import sg.edu.nus.LAPS.services.UserCredentialsService;

@Controller
public class AuthenticationController {
    // DI for all the objects

    @Autowired
    UserCredentialsService userCredentialsService;
    
    @Autowired
    EmployeeRepository employeeRepository;







    @RequestMapping("/")
    public String getWelcomePage()
    {
        return "welcome";
    }

    @RequestMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("usercredentials",new UserCredentials());
        return "login";
    }


    @RequestMapping("/authenticate")
    public String authenticate(@ModelAttribute ("usercredentials") @Valid UserCredentials userCredentials,
                               HttpSession httpSession,
                               BindingResult bindingResult,
                               Model model)
    {

        System.out.println("incoming data--------------------");
        System.out.println(userCredentials.getUsername());
        System.out.println(userCredentials.getPassword());


        SessionController sessionController = new SessionController();
        if(bindingResult.hasErrors())
        {
            return "login";
        }

        else
        {
            UserCredentials userCredentials1 = userCredentialsService
                    .findUserCredentialByUserNameAndPassword(userCredentials.getUsername()
                            ,userCredentials.getPassword());

            if (userCredentials1 == null)
            {
                return "login";
            }

            System.out.println("Will render the home page now");
            sessionController.setUserCredentials(userCredentials1);
            sessionController.setEmployee(userCredentials1.getEmployee());
            ArrayList<Employee> subordinates = (ArrayList<Employee>)employeeRepository.findEmployeesByManager_EmployeeId(userCredentials1.getEmployee().getEmployeeId());
			if (subordinates != null) {
				sessionController.setSubordinates(subordinates);
			}

            model.addAttribute("username",userCredentials1.getUsername());
            httpSession.setAttribute("userSession", sessionController);
            return "home";

        }

    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "welcome";

    }
}
