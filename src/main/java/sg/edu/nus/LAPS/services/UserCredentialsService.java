package sg.edu.nus.LAPS.services;

import sg.edu.nus.LAPS.model.UserCredentials;

import java.util.List;

public interface UserCredentialsService {


    List<UserCredentials> findAllUserCredentials();

    UserCredentials findUserCredentialById(Integer userId);

    UserCredentials findUserCredentialByUserNameAndPassword(String username,String password) ;

    UserCredentials findByUserId(Integer id);




}
