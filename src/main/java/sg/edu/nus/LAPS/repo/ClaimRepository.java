package sg.edu.nus.LAPS.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.edu.nus.LAPS.model.Claim;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Integer> {

    @Query("SELECT claims from Claim claims WHERE claims.employee.employeeId =: employeeId")
    public List<Claim> findClaimByEmployeeEmployeeId(@Param("employeeId") Integer employeeId);


}
