package sg.edu.nus.LAPS.services;

import sg.edu.nus.LAPS.model.Claim;
import sg.edu.nus.LAPS.model.Employee;

import java.util.List;

public interface ClaimService {

    public boolean saveClaimRequest(Claim claim);
    public List<Claim> findAllClaims();


}
