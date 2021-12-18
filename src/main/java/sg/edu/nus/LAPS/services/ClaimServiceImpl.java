package sg.edu.nus.LAPS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.LAPS.model.Claim;
import sg.edu.nus.LAPS.repo.ClaimRepository;

import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService{
    @Autowired
    ClaimRepository claimRepository;

    @Override
    public boolean saveClaimRequest(Claim claim) {
        if(claimRepository.save(claim)!=null) return true; else return false;
    }

    @Override
    public List<Claim> findAllClaims() {
       return claimRepository.findAll();
    }
}
