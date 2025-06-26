package domain.services;

import domain.entities.Book;
import domain.entities.Customer;
import domain.entities.Loan;
import domain.exceptions.LoanAlreadyMade;
import domain.repositories.LoanRepository;

import java.time.LocalDate;
import java.util.List;

public class LoanService {
    private final LoanRepository repository;

    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }

    public void makeLoan(Customer customer, Book book) {
        if (!book.getAvailable()) {
            throw new LoanAlreadyMade("Este livro já está emprestado.");
        }

        book.setAvailable(false);
        Loan loan = new Loan(customer, book, LocalDate.now());
        repository.save(loan);
    }

    public void returnLoan(Customer customer, Book book) {
        Loan loanToReturn = repository.findLoansByCustomer(customer).stream()
                .filter(loan -> loan.getBook().equals(book))
                .findFirst()
                .orElse(null);

        if (loanToReturn != null) {
            loanToReturn.setLoanReturnDate(LocalDate.now());
            book.setAvailable(true);
        }
    }

    public List<Loan> findAllLoans() {
        return repository.findAll();
    }

    public List<Loan> findLoansByCustomer(Customer customer) {
        return repository.findLoansByCustomer(customer);
    }

    public Loan findLoanByBookTitle(String title) {
        return repository.findLoanByBookTitle(title);
    }
}
