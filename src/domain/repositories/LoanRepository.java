package domain.repositories;

import domain.entities.Book;
import domain.entities.Customer;
import domain.entities.Loan;

import java.util.List;

public interface LoanRepository {
    void save(Loan loan);
    List<Loan> findLoansByCustomer(Customer customer);
    void returnLoan(Customer customer, Book book);
    List<Loan> findAll();
    Loan findLoanByBookTitle(String bookTitle);
}
