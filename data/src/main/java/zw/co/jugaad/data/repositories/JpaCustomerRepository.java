package zw.co.jugaad.data.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.jugaad.data.entities.CustomerData;

import java.util.Optional;

public interface JpaCustomerRepository extends JpaRepository<CustomerData, Long> {
    boolean existsByEmail(String email);

    Optional<CustomerData> findByEmail(String email);
}
