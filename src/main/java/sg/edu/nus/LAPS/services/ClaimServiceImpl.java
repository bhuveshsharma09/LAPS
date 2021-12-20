package sg.edu.nus.LAPS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sg.edu.nus.LAPS.model.Claim;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.repo.ClaimRepository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService{
    // inject the Claim Repository to interact with the
    // Claim Table in Database
    @Autowired
    ClaimRepository claimRepository;

    // save the Claims in Claim table
    @Override
    public boolean saveClaimRequest(Claim claim) {
        if(claimRepository.saveAndFlush(claim)!=null) return true; else return false;
    }

    // find all the claims in the Claim table
    @Override
    public List<Claim> findAllClaims() {
        return claimRepository.findAll();
    }

    @Override
    @Transactional
    public Claim findClaimById(Integer id) {
       // return claimRepository.findClaimById(id);
       //
        return claimRepository.findById(id).orElse(new Claim());
    }

    // filter claims, based on Employee Id
    @Override
    public List<Claim> findClaimByEmployeeId(Integer employeeId) {
        List<Claim> claimList = claimRepository.findClaimByEmployeeEmployeeId(employeeId);
        return claimList;

    }

    // create a row in Claim table
    @Override
    public Claim createClaim(Claim claim) {
        return claimRepository.saveAndFlush(claim);
    }

    // edit the content in Claim table row
    @Override
    public Claim changeClaim(Claim claim) {
        return claimRepository.saveAndFlush(claim);


        //return null;
    }

    // remove a row from Claim table
    @Override
    public void removeClaim(Claim claim) {
        claimRepository.delete(claim);
    }

    // filter all the claims from Claim table
    // based on employee id and approvalStatus
    @Override
    public List<Claim> findPendingClaimsByEmployeeId(Integer employeeId) {
        List<Claim> claimList = claimRepository.findPendingClaimsByEmployeeEmployeeId(employeeId);
        return claimList;
    }

    @Override
    public List<Claim> findLastClaim() {
        return claimRepository.findAllClaimsSorted();
    }
}
