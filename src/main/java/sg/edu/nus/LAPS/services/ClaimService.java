package sg.edu.nus.LAPS.services;

import java.util.List;

import sg.edu.nus.LAPS.model.Claim;

public interface ClaimService {

    public boolean saveClaimRequest(Claim claim);
    public List<Claim> findAllClaims();


}
