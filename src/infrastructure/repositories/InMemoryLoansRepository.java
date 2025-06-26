package infrastructure.repositories;

import domain.entities.Book;
import domain.entities.Customer;
import domain.entities.Loan;
import domain.exceptions.LoanAlreadyMade;
import domain.repositories.LoanRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryLoansRepository implements LoanRepository {
    private final Map<Customer, List<Loan>> loans = new HashMap<>();

    @Override
    public void save(Loan loan) {
        loans.computeIfAbsent(loan.getCustomer(), k -> new ArrayList<>()).add(loan);
    }

    @Override
    public List<Loan> findLoansByCustomer(Customer customer) {
        return loans
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getId().equals(customer.getId()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(List.of());
    }

    @Override
    public void returnLoan(Customer customer, Book book) {
        loans.entrySet().stream()
                .filter(entry -> entry.getKey().getId().equals(customer.getId()))
                .findFirst()
                .ifPresent(entry -> {
                    List<Loan> customerLoanList = entry.getValue();

                    Loan loan = customerLoanList.stream().filter(customerLoan -> customerLoan.getBook().getTitle().equalsIgnoreCase(book.getTitle()))
                            .findFirst()
                            .orElse(null);

                    if (loan != null) {
                        loan.getBook().setAvailable(true);
                        loan.setLoanReturnDate(LocalDate.now());
                    }
                });
    }

    @Override
    public List<Loan> findAll() {
        return loans
                .entrySet()
                .stream()
                .flatMap(entry -> entry.getValue().stream())
                .toList();
    }

    @Override
    public Loan findLoanByBookTitle(String bookTitle) {
        return loans
                .values()
                .stream()
                .flatMap(List::stream)
                .filter(loan -> loan.getBook().getTitle().equalsIgnoreCase(bookTitle.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}
