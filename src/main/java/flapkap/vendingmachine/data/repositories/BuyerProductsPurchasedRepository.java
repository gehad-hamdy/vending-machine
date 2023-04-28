package flapkap.vendingmachine.data.repositories;

import flapkap.vendingmachine.data.entities.BuyerProductsPurchased;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerProductsPurchasedRepository extends JpaRepository<BuyerProductsPurchased, Long>, JpaSpecificationExecutor<BuyerProductsPurchased> {
}
