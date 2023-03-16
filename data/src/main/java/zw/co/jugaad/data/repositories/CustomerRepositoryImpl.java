package zw.co.jugaad.data.repositories;


import org.springframework.stereotype.Component;
import zw.co.jugaad.data.entities.CustomerData;
import zw.co.jugaad.domain.Customer;
import zw.co.jugaad.usecases.customer.CustomerRepository;

import java.util.Optional;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {
    private JpaCustomerRepository repository;

    public CustomerRepositoryImpl(JpaCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer persist(Customer customer) {
        final CustomerData customerData = CustomerData.from(customer);
        return repository.save(customerData).fromThis();
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {

        return Optional.of(repository.findByEmail(email).get().fromThis());
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.of(repository.findById(id).get().fromThis());
    }
}
