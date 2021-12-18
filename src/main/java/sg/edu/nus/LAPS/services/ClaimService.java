package sg.edu.nus.LAPS.services;

import java.util.List;

import sg.edu.nus.LAPS.model.Claim;
import sg.edu.nus.LAPS.model.Employee;

public interface ClaimService {

    public boolean saveClaimRequest(Claim claim);

    public List<Claim> findAllClaims();

    public List<Claim> findClaimByEmployeeId(Integer employeeId);

    public Claim createClaim(Claim claim);

    public Claim changeClaim(Claim claim);

    public void removeClaim(Claim claim);

    List<Claim> findPendingClaimsByEmployeeId(Integer employeeId);







}
