package flapkap.vendingmachine.data.repositories;

import flapkap.vendingmachine.data.entities.UserAccountDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountDepositRepository extends JpaRepository<UserAccountDeposit, Long>, JpaSpecificationExecutor<UserAccountDeposit> {
    Optional<UserAccountDeposit> findFirstByUserIdOrderByCreatedAtDesc(Long userId);
}
