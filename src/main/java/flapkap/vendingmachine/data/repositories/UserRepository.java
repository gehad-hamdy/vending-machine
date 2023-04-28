package flapkap.vendingmachine.data.repositories;

import flapkap.vendingmachine.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT u FROM users as u Join user_tokens ut on ut.user_id = u.id  t WHERE ut.token = :token", nativeQuery = true)
    Optional<User> findByToken(@Param("token") final String token);
}
