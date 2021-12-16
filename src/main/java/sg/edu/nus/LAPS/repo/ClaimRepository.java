package sg.edu.nus.LAPS.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.LAPS.model.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Integer> {

}
