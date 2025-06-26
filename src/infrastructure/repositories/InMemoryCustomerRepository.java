package infrastructure.repositories;

import domain.entities.Customer;
import domain.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final List<Customer> customers = new ArrayList<>();

    @Override
    public void save(Customer customer) {
        customers.add(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customers.stream().toList();
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return customers
                .stream()
                .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
}
