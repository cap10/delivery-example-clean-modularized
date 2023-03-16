package zw.co.jugaad.usecases.customer;


import zw.co.jugaad.domain.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Customer persist(Customer customer);

    boolean existsByEmail(String email);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findById(Long id);
}
