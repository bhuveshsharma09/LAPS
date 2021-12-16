package sg.edu.nus.LAPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.model.UserCredentials;
import sg.edu.nus.LAPS.repo.LeaveApplicationRepository;
import sg.edu.nus.LAPS.repo.UserCredentialsRepository;

@SpringBootApplication
public class LapsApplication {

	@Autowired
	UserCredentialsRepository userCredentialsRepository;

	@Autowired
	LeaveApplicationRepository leaveApplicationRepository;

	public static void main(String[] args) {
		SpringApplication.run(LapsApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {

			/*UserCredentials user1 = new UserCredentials(7,"user1","12345");
			UserCredentials user2 = new UserCredentials(7,"user1","12345");
			UserCredentials user3 = new UserCredentials(7,"user1","12345");
			UserCredentials user4 = new UserCredentials(7,"user1","12345");
			UserCredentials user5 = new UserCredentials(7,"user1","12345");

			Employee employee1 = new Employee();


			LeaveApplication leaveApplication1 = new LeaveApplication();


*/




		};
	}

}
