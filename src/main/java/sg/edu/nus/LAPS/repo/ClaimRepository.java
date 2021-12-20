package sg.edu.nus.LAPS.repo;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.edu.nus.LAPS.model.ApprovalStatus;
import sg.edu.nus.LAPS.model.Claim;
import sg.edu.nus.LAPS.model.Employee;
import sg.edu.nus.LAPS.model.LeaveApplication;

import java.awt.print.Pageable;
import java.util.List;

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


    @Query("SELECT c from Claim c order by c.claimId desc")
    List<Claim> findAllClaimsSorted();

    @Query("SELECT claims FROM Claim claims JOIN claims.employee e WHERE e.managerId = :mid AND claims.approvalStatus = :status")
    List<Claim> findAllClaimsOfEmployeeByManagerIdWithStatus(@Param("mid") int mid, @Param("status") ApprovalStatus approval);


}
