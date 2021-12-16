package sg.edu.nus.LAPS.services;

import org.springframework.stereotype.Service;
import sg.edu.nus.LAPS.model.UserCredentials;
import sg.edu.nus.LAPS.repo.UserCredentialsRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService{

    @Resource
    private UserCredentialsRepository userCredentialsRepository;



    @Override
    @Transactional
    public List<UserCredentials> findAllUserCredentials() {
        List<UserCredentials> userCredentialsList = userCredentialsRepository.findAll();
        return userCredentialsList;
    }

    @Override
    public UserCredentials findUserCredentialById(Integer userId) {
        UserCredentials userCredential = userCredentialsRepository.findById(userId).orElse(null);
        return userCredential;
    }

    @Override
    public UserCredentials findUserCredentialByUserNameAndPassword(String username,String password) {
        UserCredentials userCredential = userCredentialsRepository
                .findUserCredentialsByUsernameAndPassword(username,password);
        // may need to manage null
        return userCredential;
    }
}
