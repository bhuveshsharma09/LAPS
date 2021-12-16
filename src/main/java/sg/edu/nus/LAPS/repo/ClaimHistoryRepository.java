package sg.edu.nus.LAPS.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.LAPS.model.ClaimHistory;

public interface ClaimHistoryRepository extends JpaRepository<ClaimHistory, Integer> {

}
