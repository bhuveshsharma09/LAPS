package sg.edu.nus.LAPS.services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.LAPS.model.Role;
import sg.edu.nus.LAPS.model.UserCredentials;
import sg.edu.nus.LAPS.repo.UserCredentialsRepository;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService{

    @Autowired
    UserCredentialsRepository userCredentialsRepository;



   
    @Transactional
    public ArrayList<UserCredentials> findAllUserCredentials() {
        return (ArrayList<UserCredentials>) userCredentialsRepository.findAll();

    }

    @Transactional
    public UserCredentials findUserCredentialById(Integer userId) {
        UserCredentials userCredential = userCredentialsRepository.findById(userId).orElse(null);
        return userCredential;
    }

    @Transactional
    public UserCredentials findUserCredentialByUserNameAndPassword(String username,String password) {
        UserCredentials userCredential = userCredentialsRepository
                .findUserCredentialsByUsernameAndPassword(username,password);
        // may need to manage null
        return userCredential;
    }

    @Transactional
    public UserCredentials findByUserId(Integer id) {
        UserCredentials userCredentials = userCredentialsRepository.findByUserId(id);
        return userCredentials;
    }
    
    
    // Bowen add
    
    

    @Transactional
	public UserCredentials createUser(UserCredentials user) {
		return userCredentialsRepository.saveAndFlush(user);
	}

    @Transactional
	public UserCredentials eidtUser(UserCredentials user) {
		return userCredentialsRepository.saveAndFlush(user);
	}

    @Transactional
	public void deleteUser(UserCredentials user) {
		userCredentialsRepository.delete(user);
		
	}

    @Transactional
	public Role findRoleOfUser(Integer userId) {
		return (Role) userCredentialsRepository.findById(userId).orElse(null).getRoles();
	}

    @Transactional
	public String findRoleNameOfUser(Integer userId) {
		return findRoleOfUser(userId).getRoleDesc();
		
	}
}
