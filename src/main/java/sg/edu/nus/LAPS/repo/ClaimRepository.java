package sg.edu.nus.LAPS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.LAPS.model.Claim;
import sg.edu.nus.LAPS.model.Employee;

public interface ClaimRepository extends JpaRepository<Claim, Integer> {
    /*
    * Repository to interact with 'Claim' table in DB.
    * */

    // get all the claims for a given employee Id
   // @Query("SELECT claims from Claim claims WHERE claims.employee =: employee")
    @Query("SELECT claims from Claim claims JOIN claims.employee e WHERE e.employeeId =:employeeId")
            //WHERE claims.employee.employeeId =: employeeId"
    public List<Claim> findClaimByEmployeeEmployeeId(@Param("employeeId") Integer employeeId);

    // get all the pending claims for managers to approve or reject
    @Query("SELECT claims from Claim claims WHERE claims.employee.employeeId =: employeeId " +
            "AND (claims.approvalStatus ='APPLIED' OR claims.approvalStatus = 'UPDATED')")
    public List<Claim> findPendingClaimsByEmployeeEmployeeId(@Param("employeeId") Integer employeeId);

    @Query("SELECT c from Claim c where c.claimId =: id")
    public Claim findClaimById(@Param("id") Integer id);

    @Query("SELECT e FROM Employee e where e.employeeId = :id")
    Employee findEmployeeById(@Param("id") String id);

   // @Query("FROM Claim c WHERE c.claimId = (SELECT max(cl.claimId) FROM Claim cl)")
    //FROM Claim c WHERE c.id = (SELECT max(cl.id) FROM Claim cl)
  /*  @Query("SELECT max(c.claimId) FROM Claim c")
    Claim findLastClaim();*/

    @Query("SELECT c from Claim c order by c.claimId desc")
    List<Claim> findAllClaimsSorted();


}
