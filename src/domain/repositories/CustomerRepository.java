package domain.repositories;

import domain.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    void save(Customer customer);
    List<Customer> findAll();
    Optional<Customer> findCustomerByEmail(String email);
}
