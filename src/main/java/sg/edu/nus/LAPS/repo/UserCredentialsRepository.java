package sg.edu.nus.LAPS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.LAPS.model.UserCredentials;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Integer> {
    // the queries for the database

    @Query("SELECT user from UserCredentials user WHERE user.username=:username AND user.password=:password")
    UserCredentials findUserCredentialsByUsernameAndPassword(@Param("username") String username,
                                                             @Param("password") String password);

    @Query("SELECT user from UserCredentials user WHERE user.userId=:userId")
    UserCredentials findByUserId(@Param("userId") Integer userid);





}
