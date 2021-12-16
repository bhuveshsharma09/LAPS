package sg.edu.nus.LAPS.controller;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.model.UserCredentials;

@NoArgsConstructor
@Data
public class SessionController {


    private static final long serialVersionUID = 1L;
    private UserCredentials userCredentials = null;
    private Employee employee = null;
    private List<Employee> subordinates = null;

    public SessionController(UserCredentials userCredentials, Employee employee, List<Employee> subordinates) {
        super();
        this.userCredentials = userCredentials;
        this.employee = employee;
        this.subordinates = subordinates;
    }
}
