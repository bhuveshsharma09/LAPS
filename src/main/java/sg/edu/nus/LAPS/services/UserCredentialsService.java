package sg.edu.nus.LAPS.services;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.LAPS.model.Role;
import sg.edu.nus.LAPS.model.UserCredentials;

public interface UserCredentialsService {


    ArrayList<UserCredentials> findAllUserCredentials();

    UserCredentials findUserCredentialById(Integer userId);

    UserCredentials findUserCredentialByUserNameAndPassword(String username,String password) ;

    UserCredentials findByUserId(Integer id);
    
    
    // Bowen add
    UserCredentials createUser(UserCredentials user);

    UserCredentials eidtUser(UserCredentials user);

	void deleteUser(UserCredentials user);
	
	Role findRoleOfUser(Integer userId);

	String findRoleNameOfUser(Integer userId);



}
